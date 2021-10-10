package com.pizza.application.views.drinks;

import java.util.Arrays;
import java.util.List;

import com.pizza.application.views.cart.Cart;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.pizza.application.views.MainLayout;

@PageTitle("Drinks")
@Route(value = "drinks", layout = MainLayout.class)
public class DrinksView extends Div implements AfterNavigationObserver {

    Grid<Drink> grid = new Grid<>();

    public DrinksView() {
        addClassName("drinks-view");
        setSizeFull();
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_NO_ROW_BORDERS);
        grid.addComponentColumn(drink -> createCard(drink));
        this.add(grid);
    }

    private HorizontalLayout createCard(Drink drink) {
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

        Span name = new Span(drink.getName());
        name.addClassName("name");
        header.add(name);

        Span price = new Span(String.valueOf(drink.getPriceString()));
        price.addClassName("price");
        header.add(price);

        Span description = new Span(drink.getDescription());
        description.addClassName("description");

        NumberField amount = new NumberField();
        amount.setWidth("100px");
        amount.setValue(1d);
        amount.setHasControls(true);
        amount.setMin(1);
        amount.setMax(50);

        Button addButton = new Button("Add");
        addButton.addClickListener(click -> {
            for(int i = 0; i < amount.getValue(); i++) {
                Cart.addDrink((Drink)drink.copyOf());
            }
            addButton.setText("Added");
            System.out.println(Cart.getProducts());
        });
        addButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        information.add(header, description);
        card.add(information, amount, addButton);
        return card;
    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {

        // Set some data when this view is displayed.
        List<Drink> drinks = Arrays.asList( //
                new Drink("Coca Cola", 2.30, "Pretty doable!")
        );

        grid.setItems(drinks);
    }

}
