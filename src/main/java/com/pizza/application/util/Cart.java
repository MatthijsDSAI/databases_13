package com.pizza.application.util;

import com.pizza.application.entity.*;
import com.pizza.application.service.PizzaService;
import com.pizza.application.util.Product;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Cart {

    private static List<Product> pizzas = new ArrayList<>();
    private static List<Product> drinks = new ArrayList<>();
    private static List<Product> desserts = new ArrayList<>();
    private static Customer customer;
    private static Address address;

    public Cart() {
    }

    public static Customer getCustomer() {
        return customer;
    }

    public static void setCustomer(Customer customer) {
        Cart.customer = customer;
    }

    public static Address getAddress() {
        return address;
    }

    public static void setAddress(Address address) {
        Cart.address = address;
    }

    public static List<Pizza> getPizzas() {
        List<Pizza> pCopy = new ArrayList<>();
        for(int i = 0; i < pizzas.size(); i++) {
            pCopy.add((Pizza) pizzas.get(i));
        }
        return pCopy;
    }

    public static List<Drink> getDrinks() {
        List<Drink> dCopy = new ArrayList<>();
        for(int i = 0; i < drinks.size(); i++) {
            dCopy.add((Drink) drinks.get(i));
        }
        return dCopy;
    }

    public static List<Dessert> getDesserts() {
        List<Dessert> dCopy = new ArrayList<>();
        for(int i = 0; i < desserts.size(); i++) {
            dCopy.add((Dessert) desserts.get(i));
        }
        return dCopy;
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
                totalPrice += products.get(i).getPrice();
            }
        }
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("nl", "NL"));
        String priceString = formatter.format(totalPrice);
        return priceString;
    }

    public static String getTotalPriceWithVAT() {
        List<Product> products = getProducts();
        double totalPrice = 0;
        if(products != null) {
            for(int i = 0; i < products.size(); i++) {
                totalPrice += products.get(i).getPrice();
            }
            totalPrice=totalPrice*1.09;
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
