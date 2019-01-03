package com.myCafe.dal.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "`Product`")
public class ProductEntity {
    @Id
    @Column(name = "`Id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "`Name`")
    private String name;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "product")
    private ProductInOrderEntity productInOrderEntity;

    public ProductEntity(Integer id) {
        this.id = id;
    }

    public ProductEntity() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductInOrderEntity getProductInOrderEntity() {
        return productInOrderEntity;
    }

    public void setProductInOrderEntity(ProductInOrderEntity productInOrderEntity) {
        this.productInOrderEntity = productInOrderEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntity that = (ProductEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name);
    }
}
