package com.english.service;

import com.english.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class MyUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserService userService;

    @Autowired
    public MyUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String param) throws UsernameNotFoundException {
        User user;
        if (param.contains("@")) {
            user = userService.getByEmail(param);
        } else {
            user = userService.getByUsername(param);
        }

        GrantedAuthority authority = getUserAuthority();
        return buildUserForAuthentication(user, authority);
    }

    private GrantedAuthority getUserAuthority() {
        return new SimpleGrantedAuthority("USER");
    }

    private UserDetails buildUserForAuthentication(User user, GrantedAuthority authorities) {
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), true,
                true, true, true, Collections.singletonList(authorities));
    }
}
