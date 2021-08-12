package com.Alkemy.springBoot.api.security.model;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.*;


@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
@SQLDelete(sql = "UPDATE users SET deleted=true WHERE id=?")
@Where(clause = "deleted = false")
@Entity(name = "users")
public class User implements Serializable {

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
    private Set<Role> roles = new HashSet<>();

    @Column(name = "create_date" ,updatable = false ,nullable = false )
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Column(name = "edit_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date edited;

    private Boolean deleted = Boolean.FALSE;

    public User(String firstName, String lastName, String email, String encode) {   // aa revisarr

    }
}
