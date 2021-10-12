package com.pizza.application.util;

public interface Product {

    public String getName();
    public double getPrice();
    public String getPriceString();
    public Product copyOf();
}
