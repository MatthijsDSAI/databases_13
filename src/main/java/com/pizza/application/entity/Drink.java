package com.pizza.application.entity;

import com.pizza.application.util.Product;

import javax.persistence.*;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Set;

@Entity(name = "drink")
@Table(name = "drink")
public class Drink implements Product {

    @Id
    @Column(name = "drink_id", nullable = false, updatable = false)
    @SequenceGenerator(name = "drink_sequence", sequenceName = "drink_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "drink_sequence")
    private Long drink_id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false, columnDefinition = "DECIMAL(4,2)")
    private double price;

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "drinks")
    Set<CustomerOrder> orders;

    public Drink() {

    }

    public Long getDrink_id() {
        return drink_id;
    }

    public void setDrink_id(Long drink_id) {
        this.drink_id = drink_id;
    }

    public Drink(String name, double price, String description) {
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

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public String getPriceString() {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("nl", "NL"));
        String priceString = formatter.format(price);
        return priceString;
    }

    @Override
    public Product copyOf() {
        return new Drink(name, price, description);
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
        return "Drink{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}
