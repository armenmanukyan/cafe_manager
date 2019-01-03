package com.myCafe.core.dto;

import com.myCafe.common.enums.ProductInOrderStatus;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class ProductInOrder {
    private ProductInOrderStatus status;
    private CafeProduct product;
    private CafeOrder order;
    @NotNull(message = "Is required")
    @Min(value = 1,message = "Amount should be positive")
    private Integer amount;
    private Integer id;


    public ProductInOrder() {
    }

    public ProductInOrder(CafeOrder order, CafeProduct product, Integer amount) {
        this.order = order;
        this.product = product;
        this.amount = amount;
    }

    public CafeProduct getProduct() {
        return product;
    }

    public void setProduct(CafeProduct product) {
        this.product = product;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public CafeOrder getOrder() {
        return order;
    }

    public void setOrder(CafeOrder order) {
        this.order = order;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ProductInOrderStatus getStatus() {
        return status;
    }

    public void setStatus(ProductInOrderStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductInOrder that = (ProductInOrder) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(product, that.product) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(order, that.order) &&
                status == that.status;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, product, amount, order, status);
    }
}
