package com.pizza.application.entity;

import com.pizza.application.util.Product;

import javax.persistence.*;
import java.text.NumberFormat;
import java.util.Locale;

@Entity(name = "dessert")
@Table(name = "dessert")
public class Dessert implements Product {

    @Id
    @Column(name = "dessert_id", nullable = false, updatable = false)
    @SequenceGenerator(name = "dessert_sequence", sequenceName = "dessert_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dessert_sequence")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false, columnDefinition = "DECIMAL(4,2)")
    private double price;

    @Column(name = "description")
    private String description;

    public Dessert() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Dessert(String name, double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public String getPriceString() {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("nl", "NL"));
        String priceString = formatter.format(price);
        return priceString;
    }

    @Override
    public Product copyOf() {
        return new Dessert(name, price, description);
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Dessert{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}
