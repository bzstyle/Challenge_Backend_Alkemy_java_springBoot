package com.Alkemy.springBoot.api.security.repository;

import com.Alkemy.springBoot.api.security.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <Usuario,Long>{

    Optional<Usuario> findByUserName(String userName);

    boolean existsByUserName(String userName);

    boolean existsByEmail(String email);


}
