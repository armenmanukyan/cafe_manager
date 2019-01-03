package com.myCafe.common.enums;

public enum UserRole {
    WAITER(1, "WAITER"),
    MANAGER(2, "MANAGER");


    private final int id;
    private final String type;

    UserRole(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public UserRole enumOf(String role) {
        for (UserRole userRole : UserRole.values()) {
            if (userRole.name().equalsIgnoreCase(role)) {
                return userRole;
            }
        }
        return null;
    }

    public int getId() {
        return this.id;
    }

    public String getType() {
        return this.type;
    }
}
