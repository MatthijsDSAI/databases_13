package com.pizza.application.entity;

import javax.persistence.*;

@Entity(name = "order")
@Table(name = "order")
public class Order {

    @Id
    @Column(name = "order_id", nullable = false, updatable = false)
    @SequenceGenerator(name = "order_sequence", sequenceName = "order_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_sequence")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "street_name", nullable = false)
    private String streetName;

    @Column(name = "house_number", nullable = false)
    private int number;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}