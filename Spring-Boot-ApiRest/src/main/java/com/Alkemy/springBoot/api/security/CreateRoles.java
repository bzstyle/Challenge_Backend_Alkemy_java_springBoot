package com.Alkemy.springBoot.api.security;

import com.Alkemy.springBoot.api.security.enums.ERole;
import com.Alkemy.springBoot.api.security.model.Role;
import com.Alkemy.springBoot.api.security.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CreateRoles implements CommandLineRunner {

    @Autowired
    RoleService roleService;

    @Override
    public void run(String... args) throws Exception {

        Role roleUser = new Role(ERole.ROLE_USER);
        Role roleAdmin = new Role(ERole.ROLE_ADMIN);



    }
}
