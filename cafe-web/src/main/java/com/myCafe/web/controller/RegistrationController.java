package com.myCafe.web.controller;

import com.myCafe.common.enums.UserRole;
import com.myCafe.core.dto.CafeUser;
import com.myCafe.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/register")
    public String registerWaiter(ModelMap map) {
        map.addAttribute("userRoles", UserRole.values());
        return "register";
    }


    @PostMapping(value = "/register")
    @Transactional
    public String registerUser(@ModelAttribute CafeUser user) {
        switch (user.getRole()) {
            case WAITER:
                user.setRole(UserRole.WAITER);
                userService.addUser(user);
                return "redirect:/waiter";

            case MANAGER:
                user.setRole(UserRole.MANAGER);
                userService.addUser(user);
                return "redirect:/manager/home";
        }

        return "redirect/register";

    }
}
