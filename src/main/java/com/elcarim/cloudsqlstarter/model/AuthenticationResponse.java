package com.elcarim.cloudsqlstarter.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {
    private String jwt;
    private String refreshToken;
    private long expiresIn;

    public AuthenticationResponse(String jwt){
        this.jwt = jwt;
    }

    public AuthenticationResponse(String token, String refreshToken) {
        this.jwt = token;
        this.refreshToken = refreshToken;
    }

}
