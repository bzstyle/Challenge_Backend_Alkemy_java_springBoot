package com.Alkemy.springBoot.api.security.service;

import com.Alkemy.springBoot.api.security.model.UserDetailClass;
import com.Alkemy.springBoot.api.security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImp implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByFirstName(username).get();
        return UserDetailClass.build(user);
    }
}
