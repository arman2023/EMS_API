package com.sanket.ems.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Table(name = "employee")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Employee {

    @Column(name = "emp_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employeeId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "address")
    private String address;

    @Column(name = "is_active")
    private boolean isActive;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "department", referencedColumnName = "department_name")
    private Department departmentFk;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "employees_roles",
            joinColumns = @JoinColumn(name = "emp_id", referencedColumnName = "emp_id"),
            inverseJoinColumns = @JoinColumn(name = "role_name", referencedColumnName = "role_name"))
    private Set<Role> roles;
}
