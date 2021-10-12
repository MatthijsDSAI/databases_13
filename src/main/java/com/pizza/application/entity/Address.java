package com.pizza.application.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Table(name = "address")
@Entity
public class Address {

    @Id
    @Column(name = "address_id", nullable = true, updatable = false)
    @SequenceGenerator(name = "address_sequence", sequenceName = "address_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_sequence")
    private Long id;

    @OneToMany(mappedBy = "address")
    private Set<Customer> customers = new HashSet<>();

    @OneToOne(mappedBy = "address")
    private CustomerOrder customerOrder;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "street_name")
    private String streetName;

    @Column(name = "house_number")
    private String houseNumber;

    public Address(String postalCode, String streetName, String houseNumber) {
        this.postalCode = postalCode;
        this.streetName = streetName;
        this.houseNumber = houseNumber;
    }

    public Address() {

    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}