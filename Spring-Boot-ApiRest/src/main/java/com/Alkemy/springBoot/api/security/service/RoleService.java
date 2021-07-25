package com.Alkemy.springBoot.api.security.service;

import com.Alkemy.springBoot.api.security.enums.ERole;
import com.Alkemy.springBoot.api.security.model.Role;
import com.Alkemy.springBoot.api.security.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public Optional<Role> getByRoleName(ERole eRole){
        return roleRepository.findByRoleName(eRole);
    }
}
