package com.myCafe.core.dto;

import com.myCafe.common.enums.OrderStatus;

import java.util.List;
import java.util.Objects;

public class CafeOrder {
    private Integer id;
    private CafeTable table;
    private OrderStatus status;
    private List<ProductInOrder> products;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CafeTable getTable() {
        return table;
    }

    public void setTable(CafeTable table) {
        this.table = table;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public List<ProductInOrder> getProducts() {
        return products;
    }

    public void setProducts(List<ProductInOrder> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CafeOrder order = (CafeOrder) o;
        return Objects.equals(id, order.id) &&
                Objects.equals(table, order.table) &&
                status == order.status ;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, table, status, products);
    }
}
