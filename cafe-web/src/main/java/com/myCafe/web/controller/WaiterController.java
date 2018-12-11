package com.myCafe.web.controller;

import com.myCafe.common.dto.PagerModel;
import com.myCafe.core.dto.CafeOrder;
import com.myCafe.core.dto.CafeTable;
import com.myCafe.core.dto.ProductInOrder;
import com.myCafe.core.service.OrderService;
import com.myCafe.core.service.ProductInOrderService;
import com.myCafe.core.service.TableService;
import com.myCafe.core.service.UserService;
import com.myCafe.web.security.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class WaiterController extends BaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private TableService tableService;
    @Autowired
    private ProductInOrderService productInOrderService;

    @GetMapping(value = "/waiter")
    public String waiter(ModelMap map, @RequestParam("pageSize") Optional<Integer> pageSize,
                         @RequestParam("page") Optional<Integer> page) {
        AuthUser authUser = assureAuthenticatedUser();
        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
        Page<CafeOrder> orders = orderService.findAllPageable(new PageRequest(evalPage, evalPageSize));
        List<ProductInOrder> productsInorder = productInOrderService.getProductInOrderForWaiter(authUser.getUserId());
        PagerModel pager = new PagerModel(orders.getTotalPages(), orders.getNumber(), BUTTONS_TO_SHOW);
        List<CafeTable> waiterTables = tableService.getTablesByUserId(authUser.getUserId());
        List<CafeOrder> tableOrders = new ArrayList<>();
        Set<ProductInOrder> productsInOrder = new HashSet<>();

        for (CafeTable table : waiterTables) {
            tableOrders.addAll(orderService.getOrdersByTableId(table.getId()));
        }
        for (CafeOrder order : tableOrders) {
            productsInOrder.addAll(productInOrderService.getOrderProducts(order.getId()));
        }
        map.addAttribute("productsInorder",productsInorder);
        map.addAttribute("tables", waiterTables);
        map.addAttribute("orders", orders);
        map.addAttribute("tableOrders", tableOrders);
        map.addAttribute("orderedProducts", productsInOrder);
        map.addAttribute("selectedPageSize", evalPageSize);
        map.addAttribute("pageSizes", PAGE_SIZES);
        map.addAttribute("pager", pager);

        return "waiter";
    }
}
