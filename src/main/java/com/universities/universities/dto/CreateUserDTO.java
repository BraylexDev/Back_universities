package com.universities.universities.dto;


public class CreateUserDTO {
    private String userName;
    private String password;
    private String email;
    private String name;
    
    public CreateUserDTO() {
    }
    public CreateUserDTO(String userName, String password, String email, String name) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.name = name;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String username) {
        this.userName = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    
    
}
