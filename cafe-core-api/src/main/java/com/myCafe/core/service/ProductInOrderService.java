package com.myCafe.core.service;

import com.myCafe.core.dto.CafeOrder;
import com.myCafe.core.dto.ProductInOrder;

import java.util.List;

public interface ProductInOrderService {

    /**
     * Get product in order by id
     *
     * @param id
     * @return
     */
    ProductInOrder getProductInOrder(Integer id);

    /**
     * Get orders by order id.
     *
     * @param orderId
     * @return products which order id is provided id
     */
    List<ProductInOrder> getOrderProducts(Integer orderId);

    /**
     * Add product into order.
     *
     * @return
     */
    CafeOrder addProductInOrder(ProductInOrder productInOrder);

    /**
     * Get all product in orders for specific user
     *
     * @param userId
     * @return
     */
    List<ProductInOrder> getProductInOrderForWaiter(Integer userId);
}
