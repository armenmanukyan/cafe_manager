package com.myCafe.web.security;

import com.myCafe.core.dto.CafeUser;
import com.myCafe.core.service.UserService;
import com.myCafe.web.security.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class  UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userservice;

    @Override
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final CafeUser user = userservice
                .getUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("No user found with username" + username));

        return new AuthUser(user);
    }
}
