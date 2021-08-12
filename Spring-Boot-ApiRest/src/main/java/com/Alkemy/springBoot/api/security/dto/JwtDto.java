package com.Alkemy.springBoot.api.security.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Setter
@Getter
public class JwtDto {

    private String token;
    private String bearer = "Bearer";
    private String firstName;
    private Collection<? extends GrantedAuthority> authorities;

    public JwtDto(String token, String firstName, Collection<? extends GrantedAuthority> authorities) {
        this.token = token;
        this.firstName = firstName;
        this.authorities = authorities;
    }


}
