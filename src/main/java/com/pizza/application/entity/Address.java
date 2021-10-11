package com.pizza.application.entity;

import javax.persistence.*;

@Table(name = "address")
@Entity
public class Address {

    @Id
    @Column(name = "address_id", nullable = false)
    private Long id;

    @OneToOne(mappedBy = "address")
    private Customer customer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}