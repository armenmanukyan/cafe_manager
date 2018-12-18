package com.myCafe.core.dto;

import java.util.List;
import java.util.Objects;

public class CafeTable {

    private Integer userId;
    private Integer Id;
    private Integer number;
    private List<CafeOrder> orders;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public List<CafeOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<CafeOrder> orders) {
        this.orders = orders;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CafeTable table = (CafeTable) o;
        return Objects.equals(Id, table.Id) &&
                Objects.equals(userId, table.userId) &&
                Objects.equals(number, table.number);
    }

    @Override
    public int hashCode() {

        return Objects.hash(Id, userId, number);
    }
}
