package com.Alkemy.springBoot.api.security.model;

import com.Alkemy.springBoot.api.security.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Setter @Getter
@SQLDelete(sql = "UPDATE Roles SET deleted=true WHERE id = ?")
@Where(clause = "deleted = false")
@Entity
@Table(name = "roles")
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Temporal(TemporalType.TIMESTAMP)
    private Date edited;

    private Boolean deleted = Boolean.FALSE;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole roleName;
}
