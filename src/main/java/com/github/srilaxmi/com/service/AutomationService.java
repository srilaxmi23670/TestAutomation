package com.github.srilaxmi.com.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.srilaxmi.com.model.Todo;
import com.github.srilaxmi.com.model.User;
import com.github.srilaxmi.com.model.UserActivity;
import com.github.srilaxmi.com.util.AutomationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.github.srilaxmi.com.constants.GlobalConstants.USERS_URL;
import static com.github.srilaxmi.com.util.AutomationUtils.getTodosUrl;

@Service
public class AutomationService {

    @Autowired
    private RestTemplate restTemplate;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public List<User> getFilteredUsersByFancodeCity() {

        try {
            String usersString = exchange(USERS_URL, HttpMethod.GET);
            User[] users = objectMapper.readValue(usersString, User[].class);

            return Stream.of(users)
                    .filter(AutomationUtils::isFancodeCityUser)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }

    }

    public List<UserActivity> getTasksCompletionPercentage() {

        try {

            List<UserActivity> userActivities = new ArrayList<>();

            List<User> users = getFilteredUsersByFancodeCity();

            for (User user: users) {

                String url = getTodosUrl(user);

                String todosString = exchange(url, HttpMethod.GET);

                Todo[] todos = objectMapper.readValue(todosString, Todo[].class);

                int completedTasks = 0;
                int totalTasks = 0;

                for (Todo todo: todos) {
                    if (todo.getCompleted()) {
                        completedTasks++;
                    }
                    totalTasks++;
                }

                double percentage = ((double) completedTasks / totalTasks) * 100;
                UserActivity userActivity = new UserActivity(user.getId(), percentage);
                userActivities.add(userActivity);

            }

            return userActivities;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private double calculateCompletionPercentage(List<Todo> todos) {
        if (todos.isEmpty()) {
            return 0;
        }

        long completedCount = todos.stream().filter(Todo::getCompleted).count();
        return (completedCount * 100.0) / todos.size();
    }

    private String exchange(String url, HttpMethod method) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);

        return restTemplate.exchange(url, method, requestEntity, String.class).getBody();

    }

}
