package com.myCafe.core.service;

import com.myCafe.common.enums.OrderStatus;
import com.myCafe.core.dto.CafeOrder;
import com.myCafe.core.dto.ProductInOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {


    /**
     * Check if order is open
     *
     * @param tableId
     * @return
     */
    boolean hasOpenOrder(final Integer tableId);

    /**
     * Open new order
     *
     * @param cafeOrder
     */
    public void openOrder(final CafeOrder cafeOrder);

    /**
     * Get all orders
     *
     * @return
     */
    public List<CafeOrder> getAll();


    /**
     * Get order by id
     *
     * @param id
     * @return
     */
    public CafeOrder getOrder(final Integer id);

    /**
     * Get list of order by status
     *
     * @param orderStatus
     * @return
     */
    public List<CafeOrder> getOrdersByStatus(OrderStatus orderStatus);

    /**
     * Finds a "page" of persons
     *
     * @param pageable
     * @return {@link Page} instance
     */
    Page<CafeOrder> findAllPageable(Pageable pageable);


    /**
     * Finds a "page" of persons
     *
     * @param pageable
     * @return {@link Page} instance
     */
    Page<CafeOrder> findAllByTableId(Pageable pageable, Integer tableId);

    /**
     * Get order by table id in which order is opened
     *
     * @param tableId
     * @return
     */
    public List<CafeOrder> getOrdersByTableId(final Integer tableId);


    /**
     * Cancel order by id.
     *
     * @param orderId
     */
    public void cancelOrder(final Integer orderId);

    /**
     * Close order by id
     *
     * @param id
     */
    public void closeOrder(final Integer id);

    /**
     * Remove product from order
     *
     * @param orderId
     * @param productId
     */
    void removeProductFromOrder(final Integer orderId, final Integer productId);

    /**
     * Edit order.
     *
     * @param order
     */
    void updateStatus(CafeOrder order);
}
