package com.myCafe.dal.entities;

import com.myCafe.common.enums.ProductInOrderStatus;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "`ProductInOrder`")
public class ProductInOrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
   // @JoinColumn(name = "`OrderId`")
    private OrderEntity order;

    @ManyToOne(fetch = FetchType.LAZY)
   // @JoinColumn(name = "`ProductId`")
    private ProductEntity product;

    @Column(name = "`Amount`")
    private Integer amount;

    @Column(name = "`Status`", nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductInOrderStatus status;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
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
        ProductInOrderEntity that = (ProductInOrderEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(order, that.order) &&
                Objects.equals(product, that.product) &&
                Objects.equals(amount, that.amount) &&
                status == that.status;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, order, product, amount, status);
    }
}
