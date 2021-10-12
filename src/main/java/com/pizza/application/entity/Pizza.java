package com.pizza.application.entity;

import com.pizza.application.util.Product;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.text.NumberFormat;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
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

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "pizza_ingredient", joinColumns = @JoinColumn(name = "pizza_id"), inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    Set<Ingredient> ingredients;

    @ManyToMany(mappedBy = "pizzas", cascade = CascadeType.MERGE)
    Set<CustomerOrder> orders;

    public Pizza() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pizza(String name, Set<Ingredient> ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        double price  = 0;
        if(ingredients != null) {
            for(Ingredient i : ingredients) {
                price += i.getPrice();
            }
        }
        return price;
    }

    @Override
    public String getPriceString() {
        double price  = 0;
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("nl", "NL"));
        Hibernate.initialize(ingredients);
        if(ingredients != null) {
            for(Ingredient i : ingredients) {
                price += i.getPrice();
            }
        }
        String priceString = formatter.format(price);
        return priceString;
    }

    @Override
    public Product copyOf() {
        Set<Ingredient> copy = new HashSet<>();
        copy.addAll(ingredients);
        return new Pizza(name, copy);
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        Iterator<Ingredient> itr = ingredients.iterator();
        String description = "";
        for(int i = 0; i < ingredients.size(); i++) {
            if(i < ingredients.size()-1) {
                description += itr.next().getIngredientName() + ", ";
            }
            else {
                description += itr.next().getIngredientName() + ".";
            }
        }


        if(description.length() > 2) {
            description = description.substring(0, 1).toUpperCase() + description.substring(1);
        }

        return description;
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
