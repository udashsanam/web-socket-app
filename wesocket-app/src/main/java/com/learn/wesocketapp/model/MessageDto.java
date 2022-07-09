package com.learn.wesocketapp.model;

public class MessageDto {

    private String username;

    private String message;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MessageDto(String username, String message) {
        this.username = username;
        this.message = message;
    }
}
