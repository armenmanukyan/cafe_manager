package com.myCafe.dal.repository;

import com.myCafe.common.enums.OrderStatus;
import com.myCafe.dal.entities.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {

    List<OrderEntity> findByTableId(Integer tableId);

    List<OrderEntity> findByStatus(OrderStatus orderStatus);

    Page<OrderEntity> findAllByTableId(Integer tableId, Pageable pageable);

    List<OrderEntity> findAllByTableIdIn(Set<Integer> ids);
}
