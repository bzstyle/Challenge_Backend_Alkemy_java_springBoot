package com.Alkemy.springBoot.api.security.service;

import com.Alkemy.springBoot.api.security.model.User;
import com.Alkemy.springBoot.api.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Optional<User> getByFirstName(String firstName){
        return userRepository.findByFirstName(firstName);
    }

    public boolean existsByFirstName(String firstName){
        return userRepository.existsByFirstName(firstName);
    }

    public boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }

    public void save(User user){
        userRepository.save(user);
    }
}
