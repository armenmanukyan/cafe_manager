package com.myCafe.common.enums;

public enum OrderStatus {
    OPEN(1, "OPEN"),
    CANCELLED(2, "CANCELLED"),
    CLOSED(3, "CLOSED");

    private final int id;
    private final String type;

    OrderStatus(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public OrderStatus enumOf(String role) {
        for (OrderStatus status : OrderStatus.values()) {
            if (status.name().equalsIgnoreCase(role)) {
                return status;
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
