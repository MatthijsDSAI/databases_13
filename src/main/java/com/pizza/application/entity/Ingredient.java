package com.pizza.application.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Table(name = "ingredient")
@Entity(name = "ingredient")
public @Data class Ingredient {

    @Id
    @Column(name = "ingredient_id", nullable = false, updatable = false)
    @SequenceGenerator(name = "ingredient_sequence", sequenceName = "ingredient_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ingredient_sequence")
    private Long ingredient_id;

    @Column(name = "ingredient_name", nullable = false)
    private String ingredientName;

    @Column(name = "diet", nullable = false)
    private String diet;

    @Column(name = "price", nullable = false, columnDefinition = "DECIMAL(4,2)")
    private double price;

    @ManyToMany(mappedBy = "ingredients")
    Set<Pizza> pizzas;

    // dessert prices are VAT exclusive
    public double getPrice() {
        return price;
    }

    public String getDiet() {
        return diet;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    public Long getIngredient_id() {
        return ingredient_id;
    }

}