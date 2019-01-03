package com.myCafe.core.converter;

import com.myCafe.core.dto.*;
import com.myCafe.dal.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.stream.Collectors;

@Component
public class Converter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserEntity toEntity(CafeUser user) {
        if (user == null) return null;
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        if (!CollectionUtils.isEmpty(user.getAssignedTables())) {
            userEntity.setAssignedTables(user.getAssignedTables().stream().map(e -> toEntity(e)).collect(Collectors.toSet()));
        }
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        userEntity.setUserName(user.getUserName());
        userEntity.setRole(user.getRole());

        return userEntity;
    }

    public CafeUser toModel(UserEntity userEntity) {
        if (userEntity == null) return null;
        CafeUser cafeUser = new CafeUser();
        cafeUser.setFirstName(userEntity.getFirstName());
        cafeUser.setId(userEntity.getId());
        cafeUser.setLastName(userEntity.getLastName());
        cafeUser.setPassword(userEntity.getPassword());
        cafeUser.setRole(userEntity.getRole());
        cafeUser.setUserName(userEntity.getUserName());
        if (!CollectionUtils.isEmpty(userEntity.getAssignedTables())) {
            cafeUser.setAssignedTables(userEntity.getAssignedTables().stream().map(e -> toModel(e)).collect(Collectors.toSet()));
        }
        return cafeUser;
    }

    public TableEntity toEntity(CafeTable table) {
        if (table == null) return null;
        TableEntity tableEntity = new TableEntity();
        tableEntity.setId(table.getId());
        tableEntity.setNumber(table.getNumber());
        if (!CollectionUtils.isEmpty(table.getOrders())) {
            tableEntity.setOrders(table.getOrders().stream().map(e -> toEntity(e)).collect(Collectors.toList()));
        } else {
            tableEntity.setOrders(Collections.emptyList());
        }
        if (table.getUserId() != null) {
            tableEntity.setUser(new UserEntity(table.getUserId()));
        }
        return tableEntity;
    }

    public CafeTable toModel(TableEntity tableEntity) {
        if (tableEntity == null) return null;
        CafeTable cafeTable = new CafeTable();
        if (tableEntity.getUser() != null) {
            cafeTable.setUserId(tableEntity.getUser().getId());
        }
        cafeTable.setId(tableEntity.getId());
        cafeTable.setNumber(tableEntity.getNumber());
        if (!CollectionUtils.isEmpty(tableEntity.getOrders())) {
            cafeTable.setOrders(tableEntity.getOrders().stream().map(e -> toModel(e)).collect(Collectors.toList()));
        }
        return cafeTable;
    }

    public CafeProduct toModel(ProductEntity productEntity) {
        if (productEntity == null) return null;
        CafeProduct cafeProduct = new CafeProduct();
        cafeProduct.setId(productEntity.getId());
        cafeProduct.setName(productEntity.getName());
        return cafeProduct;
    }

    public ProductEntity toEntity(CafeProduct cafeProduct) {
        if (cafeProduct == null) return null;
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(cafeProduct.getId());
        productEntity.setName(cafeProduct.getName());
        return productEntity;
    }

    public OrderEntity toEntity(CafeOrder cafeOrder) {
        if (cafeOrder == null) return null;
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(cafeOrder.getId());
        orderEntity.setStatus(cafeOrder.getStatus());
        orderEntity.setTable(new TableEntity(cafeOrder.getTableId()));
        return orderEntity;
    }

    public CafeOrder toModel(OrderEntity orderEntity) {
        if (orderEntity == null) return null;
        CafeOrder cafeOrder = new CafeOrder();
        cafeOrder.setId(orderEntity.getId());
        cafeOrder.setStatus(orderEntity.getStatus());
        if (orderEntity.getTable() != null) {
            cafeOrder.setTableId(orderEntity.getTable().getId());
        }
        return cafeOrder;
    }

    public ProductInOrder toModel(ProductInOrderEntity productInOrderEntity) {
        if (productInOrderEntity == null) return null;
        ProductInOrder productInOrder = new ProductInOrder();
        productInOrder.setId(productInOrderEntity.getId());
        productInOrder.setAmount(productInOrderEntity.getAmount());
        productInOrder.setOrder(toModel(productInOrderEntity.getOrder()));
        productInOrder.setProduct(toModel(productInOrderEntity.getProduct()));
        productInOrder.setStatus(productInOrderEntity.getStatus());
        return productInOrder;
    }

    public ProductInOrderEntity toEntity(ProductInOrder productInOrder) {
        if (productInOrder == null) return null;
        ProductInOrderEntity productInOrderEntity = new ProductInOrderEntity();
        productInOrderEntity.setAmount(productInOrder.getAmount());
        productInOrderEntity.setId(productInOrder.getId());
        productInOrderEntity.setStatus(productInOrder.getStatus());
        productInOrderEntity.setOrder(new OrderEntity(productInOrder.getId()));
        productInOrderEntity.setProduct(new ProductEntity(productInOrder.getProduct().getId()));
        return productInOrderEntity;

    }

}
