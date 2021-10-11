package com.pizza.application.entity;

import com.pizza.application.util.Product;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Cart {

    private static List<Product> pizzas = new ArrayList<>();
    private static List<Product> drinks = new ArrayList<>();
    private static List<Product> desserts = new ArrayList<>();

    public Cart() {

    }

    public static List<Product> getPizzas() {
        return pizzas;
    }

    public static List<Product> getDrinks() {
        return drinks;
    }

    public static List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        if (!pizzas.isEmpty()) {
            products.addAll(pizzas);
        }
        if (!drinks.isEmpty()) {
            products.addAll(drinks);
        }
        if (!desserts.isEmpty()) {
            products.addAll(desserts);
        }
        if(!products.isEmpty()) {
            return products;
        }
        return null;
    }

    public static String getTotalPrice() {
        List<Product> products = getProducts();
        double totalPrice = 0;
        if(products != null) {
            for(int i = 0; i < products.size(); i++) {
                totalPrice += 1;//products.get(i).getPrice();
            }
        }
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("nl", "NL"));
        String priceString = formatter.format(totalPrice);
        return priceString;
    }

    public static void addPizza(Pizza p) {
        pizzas.add(p);
    }

    public static void addDrink(Drink d) {
        drinks.add(d);
    }

    public static void addDessert(Dessert d) {
        desserts.add(d);
    }

    public static void emptyCart() {
        pizzas.clear();
        drinks.clear();
        desserts.clear();
    }
}
