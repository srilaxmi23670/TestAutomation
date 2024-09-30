package com.github.srilaxmi.com.controller;


import com.github.srilaxmi.com.model.User;
import com.github.srilaxmi.com.model.UserActivity;
import com.github.srilaxmi.com.service.AutomationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class AutomationController {

    @Autowired
    private AutomationService automationService;

    @GetMapping("/todo-percentage")
    public ResponseEntity<List<UserActivity>> getTasksCompletionPercentage() {

        return ResponseEntity.ok(automationService.getTasksCompletionPercentage());
    }

}
