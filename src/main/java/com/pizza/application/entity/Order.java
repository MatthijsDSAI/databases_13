package com.pizza.application.entity;

import javax.persistence.*;

@Table(name = "order")
@Entity(name = "order")
public class Order {

    @Id
    @Column(name = "order_id", nullable = false)
    private Long id;

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