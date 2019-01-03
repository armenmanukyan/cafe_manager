package com.myCafe.core.service.helper;

import com.myCafe.core.dto.CafeOrder;
import com.myCafe.core.dto.CafeProduct;
import com.myCafe.core.dto.CafeUser;
import com.myCafe.core.dto.ProductInOrder;
import com.myCafe.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.Optional;

public class ServiceHelper {
    @Autowired
    private UserService userService;

    public void validateUserOnSave(CafeUser cafeUser) {

        Assert.notNull(cafeUser, "User should not be null");
        Assert.hasText(cafeUser.getFirstName(), "User's first name should not be null");
        Assert.hasText(cafeUser.getLastName(), "User's last name should not be null");
        Assert.hasText(cafeUser.getPassword(), "User's password should not be null");
        Assert.hasText(cafeUser.getUserName(), "User's username should not be null");
        Optional<CafeUser> user = userService.getUserByUsername(cafeUser.getUserName());
        Assert.isTrue(user == null || !user.isPresent(), "User with provided username already exists");
    }

    protected void validateOrder(CafeOrder order) {
        Assert.notNull(order, "Order should not be null");
        Assert.notNull(order.getStatus(), "Order status should not be null");
        Assert.notNull(order.getTableId(), "Table id should not be null");
    }

    protected void validateProductInOrder(ProductInOrder productInOrder) {
        Assert.notNull(productInOrder, "ProductInOrder should not be null");
        Assert.notNull(productInOrder.getAmount(), "Product amount should not be null");
        Assert.notNull(productInOrder.getOrder(), "Order should not be null");
        Assert.notNull(productInOrder.getStatus(), "Status should not be null");
    }
}
