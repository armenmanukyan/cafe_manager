package com.myCafe.web.controller;

import com.myCafe.common.enums.OrderStatus;
import com.myCafe.common.enums.ProductInOrderStatus;
import com.myCafe.core.dto.CafeOrder;
import com.myCafe.core.dto.CafeProduct;
import com.myCafe.core.dto.ProductInOrder;
import com.myCafe.core.service.OrderService;
import com.myCafe.core.service.ProductInOrderService;
import com.myCafe.core.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductInOrderService productInOrderService;
    @Autowired
    private OrderService orderService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping(value = "/productInOrder/add/{id}")
    @PreAuthorize("hasAuthority('WAITER')")
    public String addProductInOrder(@PathVariable Integer id, ModelMap map) {
        CafeOrder order = orderService.getOrder(id);
        ProductInOrder productInOrder = new ProductInOrder();
        map.addAttribute("order", order);
        map.addAttribute("products", productService.getAll());
        map.addAttribute("productInOrder", new ProductInOrder());
        return "addProductInOrder";
    }

    @Transactional
    @PostMapping(value = "/productInOrder/add/{id}")
    @PreAuthorize("hasAuthority('WAITER')")
    public String addProductInOrder(@PathVariable Integer id, @Valid @ModelAttribute ProductInOrder productInOrder, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "addProductInOrder";
        }
        CafeOrder cafeOrder = orderService.getOrder(id);
        Assert.isTrue(cafeOrder.getStatus().equals(OrderStatus.OPEN), "Order status should be " + OrderStatus.OPEN);
        CafeProduct cafeProduct = productService.getProductById(productInOrder.getProduct().getId());
        productInOrder.setOrder(cafeOrder);
        productInOrder.setProduct(cafeProduct);
        productInOrder.setStatus(ProductInOrderStatus.ACTIVE);
        productInOrderService.addProductInOrder(productInOrder);
        return "redirect:/waiter";
    }

    @GetMapping(value = "/productInOrder/edit/{id}")
    @PreAuthorize("hasAuthority('WAITER')")
    public String editProductInOrder(@PathVariable Integer id, ModelMap map) {
        ProductInOrder productInOrder = productInOrderService.getProductInOrder(id);
        map.addAttribute("productInOrder", productInOrder);
        map.addAttribute("statuses", ProductInOrderStatus.values());
        return "editProductInOrder";
    }

    @RequestMapping(value = "/productInOrder/edit/{id}")
    @PreAuthorize("hasAuthority('WAITER')")
    public String editProductInOrder(@PathVariable Integer id, HttpServletRequest request) {
        ProductInOrder productInOrder = productInOrderService.getProductInOrder(id);
        Integer amount = Integer.parseInt(request.getParameter("amount").trim().isEmpty() ? productInOrder.getAmount().toString() : request.getParameter("amount"));
        ProductInOrderStatus status = ProductInOrderStatus.valueOf(request.getParameter("status"));
        productInOrder.setAmount(amount);
        productInOrder.setStatus(status);
        productInOrderService.addProductInOrder(productInOrder);
        return "redirect:/waiter";
    }


    @GetMapping(value = "/product")
    @PreAuthorize("hasAuthority('MANAGER')")
    public String createProduct(ModelMap modelMap) {
        modelMap.addAttribute("cafeProduct", new CafeProduct());
        return "createProduct";
    }


    @PostMapping(value = "/product")
    @PreAuthorize("hasAuthority('MANAGER')")
    @Transactional
    public String addProduct(@Valid @ModelAttribute CafeProduct product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "createProduct";
        }
        productService.createProduct(product);
        return "redirect:/manager/home";
    }


}
