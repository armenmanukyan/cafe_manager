package com.myCafe.web.controller;

import com.myCafe.common.enums.OrderStatus;
import com.myCafe.core.dto.CafeOrder;
import com.myCafe.core.service.OrderService;
import com.myCafe.core.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private TableService tableService;

    @RequestMapping(value = "/add/{id}")
    @Transactional
    @PreAuthorize("hasAuthority('WAITER')")
    public String addOrder(@PathVariable Integer id, ModelMap map) {
        List<CafeOrder> orderList = orderService.getOrdersByTableId(id);
        if (orderList.size() > 0 && orderList.stream().anyMatch(e -> e.getStatus().equals(OrderStatus.OPEN))) {
            map.addAttribute("message", "An active order exists");
            return "redirect:/waiter";
        }
        CafeOrder order = new CafeOrder();
        order.setStatus(OrderStatus.CANCELLED);
        order.setTableId(id);
        orderService.openOrder(order);
        return "redirect:/waiter";
    }

    @GetMapping(value = "/edit/{id}")
    @PreAuthorize("hasAuthority('WAITER')")
    public String editOrder(@PathVariable Integer id, ModelMap modelMap) {
        CafeOrder order = orderService.getOrder(id);
        modelMap.addAttribute("order", order);
        modelMap.addAttribute("orderStatuses", OrderStatus.values());
        return "updateOrder";
    }

    @RequestMapping(value = "/edit/{id}")
    @Transactional
    @PreAuthorize("hasAuthority('WAITER')")
    public String editOrder(@ModelAttribute("order") CafeOrder order) {
        orderService.updateStatus(order);
        return "redirect:/waiter";
    }

}
