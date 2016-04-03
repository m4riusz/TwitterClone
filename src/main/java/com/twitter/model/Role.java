package com.twitter.model;

/**
 * Created by Mariusz on 2016-01-20.
 */
public enum Role {
    USER("user"),
    ADMIN("admin");

    private String roleName;

    Role(String role) {
        this.roleName = role;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String role) {
        this.roleName = role;
    }
}
