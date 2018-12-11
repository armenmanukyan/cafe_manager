package com.myCafe.dal.repository;

import com.myCafe.dal.entities.OrderEntity;
import com.myCafe.dal.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity,Integer> {
}
