package com.pizza.application.entity;

import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;
import java.util.Set;

@Table(name = "customer")
@Entity(name = "customer")
public class Customer {
    @Id
    @Column(name = "customer_id", nullable = false, updatable = false)
    @SequenceGenerator(name = "customer_sequence", sequenceName = "customer_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_sequence")
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @OneToMany(mappedBy = "customer")
    private Set<Order> orders;

    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}