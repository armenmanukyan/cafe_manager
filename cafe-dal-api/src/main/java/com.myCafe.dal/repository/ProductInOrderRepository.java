package com.myCafe.dal.repository;

import com.myCafe.dal.entities.OrderEntity;
import com.myCafe.dal.entities.ProductInOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface ProductInOrderRepository extends JpaRepository<ProductInOrderEntity,Integer> {

    List<ProductInOrderEntity> findByOrderId(Integer id);

    List<ProductInOrderEntity> findAllByOrderIdIn(Set<Integer> orderIds);
}
