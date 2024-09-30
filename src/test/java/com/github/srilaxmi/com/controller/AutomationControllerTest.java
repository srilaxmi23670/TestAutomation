package com.github.srilaxmi.com.controller;

import com.github.srilaxmi.com.model.UserActivity;
import com.github.srilaxmi.com.service.AutomationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AutomationControllerTest {

    @Mock
    private AutomationService automationService;

    @InjectMocks
    private AutomationController automationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetTasksCompletionPercentage() {

        List<UserActivity> mockUserActivities = new ArrayList<>(List.of(
                new UserActivity(1, 55.00000000000001d),
                new UserActivity(5, 60.0d),
                new UserActivity(5, 60.0d)
        ));

        when(automationService.getTasksCompletionPercentage()).thenReturn(mockUserActivities);

        ResponseEntity<List<UserActivity>> response = automationController.getTasksCompletionPercentage();

        verify(automationService, times(1)).getTasksCompletionPercentage();

        assertEquals(mockUserActivities, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

}


