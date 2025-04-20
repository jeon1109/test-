package com.example.jeon.dto;

public enum Role {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private final String type;

    Role(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
