package com.elcarim.cloudsqlstarter.model;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;

    public LoginRequest(String email, String password) {
    }
}
