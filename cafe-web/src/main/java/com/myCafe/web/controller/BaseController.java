package com.myCafe.web.controller;

import com.myCafe.core.dto.CafeUser;
import com.myCafe.web.security.AuthUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedCredentialsNotFoundException;
import org.springframework.stereotype.Controller;

@Controller
public class BaseController {

    static final int BUTTONS_TO_SHOW = 3;
    static final int INITIAL_PAGE = 0;
    static final int INITIAL_PAGE_SIZE = 5;
    static final int[] PAGE_SIZES = {5, 10};

    protected AuthUser assureAuthenticatedUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (AuthUser.class.isInstance(principal)) {
            return (AuthUser) principal;
        }

        throw new PreAuthenticatedCredentialsNotFoundException("Authorization is required");
    }
}
