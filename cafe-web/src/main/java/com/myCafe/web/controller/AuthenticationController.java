package com.myCafe.web.controller;

import com.myCafe.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login")
    public String login() {
        return "logIn";
    }

    @PostMapping("/checkUserName")
    @ResponseBody
    public String checkUser(@RequestParam("userName") String userName) {
        if (userService.getUserByUsername(userName) != null) {
            return "exists";
        } else return "notexists";
    }
}
