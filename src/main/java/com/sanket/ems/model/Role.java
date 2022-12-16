package com.sanket.ems.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

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

    @ManyToMany(mappedBy = "roles")
    private Collection<Employee> employees;
}
