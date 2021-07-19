package com.Alkemy.springBoot.api.model;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.*;
import java.util.stream.Collectors;


@NoArgsConstructor
@Setter @Getter
@SQLDelete(sql = "UPDATE users SET deleted=true WHERE id=?")
@Where(clause = "deleted = false")
@Entity(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "The first name is required.")
    @Column(name = "first_name" ,nullable = false)
    private String firstName;

    @NotBlank(message = "The last name is required.")
    @Column(name = "last_name" ,nullable = false)
    private String lastName;

    @NotBlank(message = "The email is required.")
    @Column(nullable = false)
    @Email
    private String email;

    @NotBlank(message = "The password is required.")
    @Column(nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;

    @Column(name = "create_date" ,updatable = false ,nullable = false )
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Column(name = "edit_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date edited;

    private Boolean deleted = Boolean.FALSE;

    @ElementCollection(targetClass = GrantedAuthority.class)
    private Collection<? extends GrantedAuthority> authorities;

    @Builder
    public User(String firstName, String lastName, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.created = new Date();
        this.authorities = authorities;
    }

    public static User build(User user) {
        List<GrantedAuthority> authorities = user.getRoles()
                .stream()
                .map(rol -> new SimpleGrantedAuthority(rol.getRoleName().name()))
                .collect(Collectors.toList());

        return new User(user.getFirstName() , user.getLastName(), user.getPassword() , user.getEmail(), authorities);
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
