package com.universities.universities.dto;

public class JwtAuthResponseDTO {
    private String accessToken;
    private String tokenType = "Bearer";
    private String username;
    private String email;
    private String rol;
    private String name;

    
    
    public JwtAuthResponseDTO() {}
    
    public JwtAuthResponseDTO(String accessToken, String username, 
                             String email, String rol, String name) {
        this.accessToken = accessToken;
        this.username = username;
        this.email = email;
        this.rol = rol;
        this.name = name;
    }
    
    // Getters y Setters
    public String getAccessToken() { return accessToken; }
    public void setAccessToken(String accessToken) { this.accessToken = accessToken; }
    
    public String getTokenType() { return tokenType; }
    public void setTokenType(String tokenType) { this.tokenType = tokenType; }
    
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getRol() { return rol; }

    public void setRol(String rol) {
        this.rol = rol;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}