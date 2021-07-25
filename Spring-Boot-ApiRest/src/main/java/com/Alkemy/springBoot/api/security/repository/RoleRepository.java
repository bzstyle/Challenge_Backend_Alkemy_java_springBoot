package com.Alkemy.springBoot.api.security.repository;

import com.Alkemy.springBoot.api.security.enums.ERole;
import com.Alkemy.springBoot.api.security.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

    Optional<Role> findByRoleName(ERole eRole);

}
