package com.pizza.application.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Table(name = "employee")
@Entity
public class Employee {

    @Id
    @Column(name = "employee_id", nullable = false, updatable = false)
    @SequenceGenerator(name = "employee_sequence", sequenceName = "employee_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_sequence")
    private Long employee_id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @OneToMany(mappedBy = "employee")
    private Set<CustomerOrder> orders;

    @Column(name = "postal_code")
    private String postalCode;


    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Employee() {

    }

    public Long getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Long employee_id) {
        this.employee_id = employee_id;
    }

}