package com.sanket.ems.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Table(name = "role")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    @Column(name = "role_name")
    @Id
    private String roleName;

    @Column(name = "description")
    private String description;

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinColumn(name = "role_name")
//    private Set<Authority> authorities;
}
