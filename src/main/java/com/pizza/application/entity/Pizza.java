package com.pizza.application.entity;

import com.pizza.application.util.Product;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "pizza")
@Table(name = "pizza")
public class Pizza implements Product {

    @Id
    @Column(name = "pizza_id", nullable = false, updatable = false)
    @SequenceGenerator(name = "pizza_sequence", sequenceName = "pizza_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pizza_sequence")
    private Long id;

    @Column(name = "name", nullable = false, length = 40)
    private String name;

    @ManyToMany
    @JoinTable(name = "pizza_ingredient", joinColumns = @JoinColumn(name = "pizza_id"), inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    Set<Ingredient> ingredients;

    public Pizza() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pizza(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public double getPrice() {
//        return price;
//    }

//    @Override
//    public String getPriceString() {
//        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("nl", "NL"));
//        String priceString = formatter.format(price);
//        return priceString;
//    }

    @Override
    public Product copyOf() {
        return new Pizza(name);
    }

//    public void setPrice(double price) {
//        this.price = price;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
}
