package com.myCafe.dal.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "`CafeTable`")
public class TableEntity {

    public TableEntity () {

    }

    public TableEntity(Integer id) {
        this.id = id;
    }

    @Id
    @Column(name = "`Id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY ,cascade = CascadeType.REFRESH)
    @JoinColumn(name = "`WaiterId`", nullable = true)
    private UserEntity user;

    @Column(name = "`Number`")
    private Integer number;

    @OneToMany
    @JoinTable(name = "`TableOrders`",joinColumns = {@JoinColumn (name = "TableId")},inverseJoinColumns = {@JoinColumn (name = "OrderId")})
    private List<OrderEntity> orders;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public List<OrderEntity> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderEntity> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TableEntity that = (TableEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(user, that.user) &&
                Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, user, number);
    }
}
