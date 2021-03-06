package com.pizza.application.views.desserts;

import com.pizza.application.entity.Dessert;
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

@PageTitle("Desserts")
@Route(value = "desserts", layout = MainLayout.class)
public class DessertsView extends Div {

    Grid<Dessert> grid = new Grid<>();

    public DessertsView(PizzaService service) {
        addClassName("desserts-view");
        setSizeFull();
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_NO_ROW_BORDERS);
        grid.addComponentColumn(dessert -> createCard(dessert));
        grid.setItems(service.findAllDesserts());
        this.add(grid);
    }

    private HorizontalLayout createCard(Dessert dessert) {
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

        Span name = new Span(dessert.getName());
        name.addClassName("name");
        header.add(name);

        Span price = new Span(String.valueOf(dessert.getPriceString()));
        price.addClassName("price");
        header.add(price);

        Span description = new Span(dessert.getDescription());
        description.addClassName("post");

        NumberField amount = new NumberField();
        amount.setWidth("100px");
        amount.setValue(1d);
        amount.setHasControls(true);
        amount.setMin(1);
        amount.setMax(50);

        Button addButton = new Button("Add");
        addButton.addClickListener(click -> {
            for(int i = 0; i < amount.getValue(); i++) {
                Cart.addDessert((Dessert)dessert.copyOf());
            }
            addButton.setText("Added");
        });
        addButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        information.add(header, description);
        card.add(information, amount, addButton);
        return card;
    }
}
