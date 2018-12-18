package com.myCafe.web.controller;

import com.myCafe.common.enums.UserRole;
import com.myCafe.core.dto.CafeTable;
import com.myCafe.core.dto.CafeUser;
import com.myCafe.core.service.TableService;
import com.myCafe.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/manager")
public class ManagerController {
    @Autowired
    private UserService userService;
    @Autowired
    private TableService tableService;

    @GetMapping(value = "/home")
    public String manager(ModelMap map) {
        map.addAttribute("waiters", userService.getWaiters());
        map.addAttribute("tables", tableService.getAll());
        return "manager";
    }

    @GetMapping(value = "/waiter")
    public String createWaiter() {

        return "createWaiter";
    }

    @PostMapping(value = "/waiter/add")
    @Transactional
    public String addWaiter(@ModelAttribute CafeUser user) {
        user.setRole(UserRole.WAITER);
        userService.addUser(user);
        return "redirect:/manager/home";
    }

    @RequestMapping(value = "/waiter/delete/{id}")
    @Transactional
    public String deleteWaiter(@PathVariable(name = "id") Integer id) {
        userService.deleteUserById(id);
        return ("redirect:/manager/home");
    }

    @GetMapping(value = "/table/update/{id}")
    public String updateTable(@PathVariable(name = "id") Integer id, ModelMap map) {
        map.addAttribute("table", tableService.getTableById(id));
        map.addAttribute("waiters", userService.getWaiters());
        return "updateTable";
    }

    @PostMapping(value = "/table/update/{id}")
    @Transactional
    public String updateTable(@PathVariable(name = "id") Integer tableId, HttpServletRequest request) {
        Integer userId = Integer.parseInt(request.getParameter("id"));
        CafeTable table = tableService.getTableById(tableId);
        CafeUser waiter = userService.getUserById(userId);
        table.setUserId(waiter.getId());
        tableService.unassignTableFromUser(table.getNumber());
        tableService.assignTableToUser(userId, table.getNumber());
        return "redirect:/manager/home";
    }

}


