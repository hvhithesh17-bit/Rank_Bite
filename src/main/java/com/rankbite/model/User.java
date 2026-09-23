package com.rankbite.model;

public class User extends Person {
    private String username;
    private String role;

    public User(int userId, String name, String username, String role) {
        super(userId, name);
        this.username = username;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public void displayInfo() {
        System.out.println("User ID: " + getId() + ", Name: " + getName() + 
                           ", Username: " + username + ", Role: " + role);
    }
}
