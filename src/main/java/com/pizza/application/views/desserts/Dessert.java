package com.pizza.application.views.desserts;

import com.pizza.application.util.Product;

import java.text.NumberFormat;
import java.util.Locale;

public class Dessert implements Product {

    private String name;
    private double price;
    private String description;

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

    @Override
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
