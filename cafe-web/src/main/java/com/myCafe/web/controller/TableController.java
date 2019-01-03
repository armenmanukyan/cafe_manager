package com.myCafe.web.controller;

import com.myCafe.core.dto.CafeTable;
import com.myCafe.core.service.TableService;
import com.myCafe.core.service.UserService;
import com.myCafe.dal.exceptions.DuplicateEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TableController {

    @Autowired
    private UserService userService;
    @Autowired
    private TableService tableService;

    @GetMapping(value = "/table")
    public String createTable(ModelMap map) {
        map.addAttribute("waiters", userService.getWaiters());
        return "createTable";
    }

    @PostMapping(value = "/table")
    @Transactional
    public String createTable(HttpServletRequest httpRequest) throws DuplicateEntityException {
        Integer tableNumber = Integer.parseInt(httpRequest.getParameter("tableNumber"));
        CafeTable table = new CafeTable();
        table.setNumber(tableNumber);
        tableService.addTable(table);
        return "redirect:manager/home";
    }
}
