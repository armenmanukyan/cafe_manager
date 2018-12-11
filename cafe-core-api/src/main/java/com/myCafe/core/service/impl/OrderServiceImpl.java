package com.myCafe.core.service.impl;

import com.myCafe.common.enums.OrderStatus;
import com.myCafe.core.converter.Converter;
import com.myCafe.core.dto.CafeOrder;
import com.myCafe.core.dto.ProductInOrder;
import com.myCafe.core.service.helper.ServiceHelper;
import com.myCafe.core.service.OrderService;
import com.myCafe.core.service.ProductService;
import com.myCafe.core.service.TableService;
import com.myCafe.dal.entities.OrderEntity;
import com.myCafe.dal.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityNotFoundException;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class OrderServiceImpl extends ServiceHelper implements OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    private Converter converter;

    @Autowired
    private TableService tableService;

    @Override
    @Transactional
    public void openOrder(CafeOrder order) {
        super.validateOrder(order);
        order.setStatus(OrderStatus.OPEN);
        orderRepository.saveAndFlush(converter.toEntity(order));
    }

    @Override
    @Transactional
    public void closeOrder(Integer id) {
        Assert.notNull(id, "Order id should not be null");
       OrderEntity orderEntity = orderRepository
                .findById(id)
                .orElseThrow(() -> {
                    String message = String.format("No order found with id " +
                            "[%s]", id);
                    LOGGER.error(message);
                    return new EntityNotFoundException(message);
                });
        orderEntity.setStatus(OrderStatus.CLOSED);
        orderRepository.saveAndFlush(orderEntity);
    }

    @Override
    public CafeOrder getOrder(Integer id) {
        Assert.notNull(id, "Order id should not be null");
        OrderEntity order = orderRepository
                .findById(id)
                .orElseThrow(() -> {
                    String message = String.format("No order found with id " +
                            "[%s]", id);
                    LOGGER.error(message);
                    return new EntityNotFoundException(message);
                });

        return converter.toModel(orderRepository.getOne(id));
    }

    @Override
    public List<CafeOrder> getOrdersByTableId(Integer tableId) {
        Assert.notNull(tableId, "Table id should not be null");
        List<OrderEntity> orders = orderRepository.findByTableId(tableId);
        if (CollectionUtils.isEmpty(orders)) {
            LOGGER.info(String.format("No order found with table id " +
                    "[%s]", tableId));
        }
        return orders.stream().map(e -> converter.toModel(e)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void cancelOrder(Integer orderId) {
        Assert.notNull(orderId, "Order id should not be null");
        OrderEntity order = orderRepository.findById(orderId).orElseThrow(() -> {
            String message = String.format("No order found with id " +
                    "[%s]", orderId);
            LOGGER.error(message);
            return new EntityNotFoundException(message);
        });

        order.setStatus(OrderStatus.CANCELLED);
        orderRepository.saveAndFlush(order);

        if (order.getTable() != null && order.getTable().getId() != null) {
            tableService.unassignTableFromUser(order.getTable().getNumber());
        }
    }

    @Override
    public List<CafeOrder> getAll() {
        return orderRepository.findAll().stream().map(e -> converter.toModel(e)).collect(Collectors.toList());
    }

    @Override
    public List<CafeOrder> getOrdersByStatus(OrderStatus orderStatus) {
        Assert.notNull(orderStatus, "Order status should not be null");
        List<OrderEntity> order = orderRepository.findByStatus(orderStatus);
        return order.stream().map(e -> converter.toModel(e)).collect(Collectors.toList());
    }

    @Override
    public boolean hasOpenOrder(Integer tableId) {
        List<CafeOrder> orders = orderRepository.findByTableId(tableId).stream().map(e -> converter.toModel(e)).collect(Collectors.toList());
        Optional<CafeOrder> any = orders.stream().filter(e -> e.getStatus().equals(OrderStatus.OPEN)).findAny();
        return any.isPresent();
    }

    @Override
    public Page<CafeOrder> findAllPageable(Pageable pageable) {
        Assert.notNull(pageable, "Pagable should not be null");
        return orderRepository.findAll(pageable).map((entity) -> {
            return this.converter.toModel(entity);
        });
    }

    @Override
    public Page<CafeOrder> findAllByTableId(Pageable pageable, Integer tableId) {
        return orderRepository.findAllByTableId(tableId, pageable).map((entity) -> {
            return this.converter.toModel(entity);
        });
    }

    @Override
    @Transactional
    public void removeProductFromOrder(Integer orderId, Integer productId) {
        OrderEntity orderEntity = orderRepository.findById(orderId).orElseThrow(() -> {
            String message = String.format("No order found with id " +
                    "[%s]", orderId);
            LOGGER.error(message);
            return new EntityNotFoundException(message);
        });

        CafeOrder order = converter.toModel(orderEntity);
        List<ProductInOrder> productsInOrder = order.getProducts();
        final Iterator<ProductInOrder> iterator = order.getProducts().iterator();
        while (iterator.hasNext()) {
            ProductInOrder productInOrder = iterator.next();
            if (productId.equals(productInOrder.getProduct().getId())) {
                iterator.remove();
            }
        }
        for (ProductInOrder productInOrder : productsInOrder) {
            if (productId.equals(productInOrder.getProduct().getId())) {
                productInOrder.setOrder(null);
                productInOrder.setProduct(null);
                order.getProducts().remove(productInOrder);
            }
        }
        orderRepository.saveAndFlush(converter.toEntity(order));
    }

    @Override
    public void updateStatus(CafeOrder order) {
        CafeOrder storedOrder = orderService.getOrder(order.getId());
        storedOrder.setStatus(order.getStatus());
        validateOrder(storedOrder);
        orderRepository.saveAndFlush(converter.toEntity(order));
    }
}
