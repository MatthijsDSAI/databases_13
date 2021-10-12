package com.pizza.application.views.pizzas;

import com.pizza.application.entity.Ingredient;
import com.pizza.application.entity.Pizza;
import com.pizza.application.service.PizzaService;
import com.pizza.application.views.MainLayout;
import com.pizza.application.util.Cart;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.*;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@PageTitle("Pizzas")
@Route(value = "pizzas", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class PizzasView extends Div {

    Grid<Pizza> grid = new Grid<>();
    private final PizzaService service;

    public PizzasView(PizzaService service) {
        this.service = service;
        addClassName("pizzas-view");
        setSizeFull();
        grid.setItems(service.findAllPizzas());
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_NO_ROW_BORDERS);
        grid.addComponentColumn(pizza -> createCard(pizza));
        this.add(grid);
    }

    private HorizontalLayout createCard(Pizza pizza) {
        HorizontalLayout card = new HorizontalLayout();
        card.addClassName("card");
        card.setSpacing(false);
        card.getThemeList().add("spacing-s");

        VerticalLayout information = new VerticalLayout();
        information.addClassName("information");
        information.setSpacing(false);
        information.setPadding(false);

        HorizontalLayout header = new HorizontalLayout();
        header.addClassName("header");
        header.setSpacing(false);
        header.getThemeList().add("spacing-s");

        Span name = new Span(pizza.getName());
        name.addClassName("name");
        header.add(name);

        Span price = new Span(String.valueOf(pizza.getPriceString()));
        price.addClassName("price");
        header.add(price);

        Span description = new Span(pizza.toString());
        description.addClassName("post");

        NumberField amount = new NumberField();
        amount.setWidth("100px");
        amount.setValue(1d);
        amount.setHasControls(true);
        amount.setMin(1);
        amount.setMax(50);

        Set<Ingredient> ingredients = pizza.getIngredients();

        boolean vegetarian = true;
        if(ingredients != null) {
            for(Ingredient i : ingredients) {
                if(!i.getDiet().equals("vegetarian")) {
                    vegetarian = false;
                    break;
                }
            }
        }

        if(vegetarian) {
            Span vegetarianSpan = new Span("Vegetarian");
            vegetarianSpan.addClassName("price");
            header.add(vegetarianSpan);
        }

        Button addButton = new Button("Add");
        addButton.addClickListener(click -> {
            for(int i = 0; i < amount.getValue(); i++) {
                Cart.addPizza((Pizza)pizza.copyOf());
            }
            addButton.setText("Added");
        });
        addButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        information.add(header, description);
        card.add(information, amount, addButton);
        return card;
    }

}
