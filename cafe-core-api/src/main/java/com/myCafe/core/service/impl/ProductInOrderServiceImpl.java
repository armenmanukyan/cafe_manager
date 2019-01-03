package com.myCafe.core.service.impl;

import com.myCafe.core.converter.Converter;
import com.myCafe.core.dto.CafeOrder;
import com.myCafe.core.dto.ProductInOrder;
import com.myCafe.core.service.ProductInOrderService;
import com.myCafe.core.service.helper.ServiceHelper;
import com.myCafe.dal.entities.OrderEntity;
import com.myCafe.dal.entities.ProductInOrderEntity;
import com.myCafe.dal.repository.OrderRepository;
import com.myCafe.dal.repository.ProductInOrderRepository;
import com.myCafe.dal.repository.TableRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductInOrderServiceImpl extends ServiceHelper implements ProductInOrderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);
    @Autowired
    private ProductInOrderRepository productInOrderRepository;
    @Autowired
    private Converter converter;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private TableRepository tableRepository;

    @Override
    public ProductInOrder getProductInOrder(Integer id) {
        Assert.notNull(id, " Id should not be null");
        ProductInOrderEntity productInOrderEntity = productInOrderRepository.findById(id).orElseThrow(() -> {
            String message = String.format("No product in order found with id " +
                    "[%s]", id);
            LOGGER.error(message);
            return new EntityNotFoundException(message);
        });
        ;

        return converter.toModel(productInOrderEntity);
    }

    @Override
    public List<ProductInOrder> getOrderProducts(Integer orderId) {
        Assert.notNull(orderId, "Order id should not be null");
        return productInOrderRepository.findByOrderId(orderId)
                .stream()
                .map(e -> converter.toModel(e))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CafeOrder addProductInOrder(ProductInOrder productInOrder) {
        validateProductInOrder(productInOrder);
        OrderEntity order = orderRepository.findById(productInOrder.getOrder().getId()).orElseThrow(() -> {
            String message = String.format("No order found with id " +
                    "[%s]", productInOrder.getOrder().getId());
            LOGGER.error(message);
            return new EntityNotFoundException(message);
        });
        ;
        ProductInOrderEntity productInOrderEntity = converter.toEntity(productInOrder);
        productInOrderEntity.setOrder(order);
        productInOrderRepository.saveAndFlush(productInOrderEntity);
        return converter.toModel(order);
    }

    @Override
    public List<ProductInOrder> getProductInOrderForWaiter(Integer userId) {
        Set<Integer> tableIds = tableRepository
                .findAllByUserId(userId)
                .stream()
                .filter(e -> e != null && e.getId() != null).map(e -> e.getId())
                .collect(Collectors.toSet());

        Set<Integer> orderIds = orderRepository
                .findAllByTableIdIn(tableIds)
                .stream()
                .filter(e -> e != null && e.getId() != null)
                .map(e -> e.getId())
                .collect(Collectors.toSet());

        return productInOrderRepository.findAllByOrderIdIn(orderIds).stream().map(e -> converter.toModel(e)).collect(Collectors.toList());
    }


}
