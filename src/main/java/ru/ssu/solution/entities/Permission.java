package ru.ssu.solution.entities;

public enum Permission {
    USERS_READ("users:read"),
    FILES_READ("files:read");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
