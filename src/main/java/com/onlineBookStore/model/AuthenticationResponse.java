package com.onlineBookStore.model;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthenticationResponse {
    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("refresh_token")
    private String refreshToken;

    @JsonProperty("message")
    private String message;

    @JsonProperty("role")
    private Role role;

    public AuthenticationResponse(String accessToken, String refreshToken, String message, Role role) {
        this.accessToken = accessToken;
        this.message = message;
        this.refreshToken = refreshToken;
        this.role = Role.valueOf(String.valueOf(role));
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getMessage() {
        return message;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = Role.valueOf(role);
    }

}
