package com.sanket.ems.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "department")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {

    @Column(name = "department_name")
    @Id
    private String departmentName;

    @Column(name = "description")
    private String description;
}
