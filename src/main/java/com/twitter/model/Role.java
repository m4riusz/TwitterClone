package com.twitter.model;

/**
 * Created by Mariusz on 2016-01-20.
 */
public enum Role {
    USER("user"),
    ADMIN("admin");

    private String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
