package com.Alkemy.springBoot.api.security.controller;


import com.Alkemy.springBoot.api.security.jwt.JwtProvider;

import com.Alkemy.springBoot.api.security.dto.JwtDto;
import com.Alkemy.springBoot.api.security.dto.NewUser;
import com.Alkemy.springBoot.api.security.dto.UserLogin;
import com.Alkemy.springBoot.api.security.enums.ERole;
import com.Alkemy.springBoot.api.security.model.Role;
import com.Alkemy.springBoot.api.security.model.Usuario;
import com.Alkemy.springBoot.api.security.service.RoleService;
import com.Alkemy.springBoot.api.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    RoleService roleService;

    @Autowired
    JwtProvider jwtProvider;




    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody NewUser newUser , BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Campos erroneos.");
        }
        if (userService.existsByUserName(newUser.getUserName())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El nombre ya existe");
        }
        if (userService.existsByEmail(newUser.getEmail())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El email ya existe");
        }
        Usuario user = new Usuario(newUser.getName(),newUser.getUserName(),
                newUser.getEmail(),passwordEncoder.encode(newUser.getPassword()));

        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getByRoleName(ERole.ROLE_USER).get());
        if (newUser.getRoles().contains("admin")){
            roles.add(roleService.getByRoleName(ERole.ROLE_ADMIN).get());
        }
        user.setRoles(roles);
        userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuario creado");
    }




    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody UserLogin userLogin,BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error en dato de algún campo.");
        }
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userLogin.getUserName(),userLogin.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(jwt,userDetails.getUsername(),userDetails.getAuthorities());

        return new ResponseEntity(jwtDto,HttpStatus.OK);
    }

}
