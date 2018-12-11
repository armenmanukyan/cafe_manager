package com.myCafe.common.enums;

public enum ProductInOrderStatus  {
    ACTIVE(1,"ACTIVE"),
    CANCELLED(2,"CANCELLED");


    private final int id;
    private final String type;

    ProductInOrderStatus(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public ProductInOrderStatus enumOf(String role) {
        for (ProductInOrderStatus status : ProductInOrderStatus.values()) {
            if (status.name().equalsIgnoreCase(role)) {
                return status;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }
}
