package com.thdtraining.todoserver.security;

import lombok.Data;

/* 10. Create a class to receive email and password */
@Data
public class AuthCredentials {
    private String email;
    private String password;
}
