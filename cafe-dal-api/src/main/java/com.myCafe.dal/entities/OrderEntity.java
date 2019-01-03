package com.myCafe.dal.entities;

import com.myCafe.common.enums.OrderStatus;
import org.springframework.stereotype.Controller;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "`Order`")
public class OrderEntity {
    @Id
    @Column(name = "`Id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
  //  @JoinColumn(name = "`TableId`")
    private TableEntity table;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<ProductInOrderEntity> productsInOrder;

    @Column(name = "`Status`", nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public OrderEntity(Integer id) {
        this.id = id;
    }

    public OrderEntity() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TableEntity getTable() {
        return table;
    }

    public void setTable(TableEntity table) {
        this.table = table;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public List<ProductInOrderEntity> getProductsInOrder() {
        return productsInOrder;
    }

    public void setProductsInOrder(List<ProductInOrderEntity> productsInOrder) {
        this.productsInOrder = productsInOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderEntity that = (OrderEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(table, that.table) &&
                status == that.status;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, table, status);
    }
}
