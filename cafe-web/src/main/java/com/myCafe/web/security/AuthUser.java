package com.myCafe.web.security;

import com.myCafe.common.enums.UserRole;
import com.myCafe.core.dto.CafeUser;
import com.myCafe.dal.entities.UserEntity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class AuthUser extends User {
    private CafeUser user;

    public AuthUser(CafeUser user) {
        super(user.getUserName(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getRole().toString()));
        this.user = user;
    }

    public CafeUser getUserEntity() {
        return user;
    }

    public Integer getUserId() {
        return user.getId();
    }

    public UserRole getRole() {
        return user.getRole();
    }
}
