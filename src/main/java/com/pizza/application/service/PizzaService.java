package com.pizza.application.service;

import com.pizza.application.entity.Drink;
import com.pizza.application.entity.Pizza;
import com.pizza.application.repository.DrinkRepository;
import com.pizza.application.repository.PizzaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {

    private final DrinkRepository drinkRepository;
    private final PizzaRepository pizzaRepository;

    public PizzaService(DrinkRepository drinkRepository,
                        PizzaRepository pizzaRepository) {
        this.drinkRepository = drinkRepository;
        this.pizzaRepository = pizzaRepository;
    }

    public List<Drink> findAllDrinks() {
        return drinkRepository.findAll();
    }

    public List<Pizza> findAllPizzas() {
        return pizzaRepository.findAll();
    }
}
