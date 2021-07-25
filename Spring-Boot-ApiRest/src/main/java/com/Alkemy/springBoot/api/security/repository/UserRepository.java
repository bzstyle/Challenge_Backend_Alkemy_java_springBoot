package com.Alkemy.springBoot.api.security.repository;

import com.Alkemy.springBoot.api.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <User,Long>{

    Optional<User> findByFirstName(String firstName);

    boolean existsByFirstName(String firstName);

    boolean existsByEmail(String email);


}
