package com.github.srilaxmi.com.model;


public class UserActivity {

    private Integer userId;
    private Double taskCompletion;

    public UserActivity(Integer userId, Double taskCompletion) {
        this.userId = userId;
        this.taskCompletion = taskCompletion;
    }

    public Double getTaskCompletion() {
        return taskCompletion;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setTaskCompletion(Double taskCompletion) {
        this.taskCompletion = taskCompletion;
    }

}
