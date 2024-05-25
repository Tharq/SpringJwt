package com.bharani.springJwt.Modal;

public class AuthenticationResponse {

    public AuthenticationResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    private String token;
}
