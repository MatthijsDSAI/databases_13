package com.pizza.application.views.confirmation;

import com.pizza.application.entity.CustomerOrder;
import com.pizza.application.entity.Employee;
import com.pizza.application.service.PizzaService;
import com.pizza.application.util.Cart;
import com.pizza.application.util.Product;
import com.pizza.application.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;

@PageTitle("Order confirmation")
@Route(value = "confirm-order", layout = MainLayout.class)
public class ConfirmationView extends VerticalLayout {

    Grid<Product> grid = new Grid<>();
    CustomerOrder order = new CustomerOrder();
    H3 confirmationHeader = new H3("Order");
    Button confirmButton = new Button("Confirm order");
    private PizzaService service;

    public ConfirmationView(PizzaService service) {
        this.service = service;
        setAlignItems(Alignment.CENTER);
        setSpacing(true);
        setPadding(true);
        addClassName("cart-view");
        setWidthFull();
        setHeightFull();

        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_NO_ROW_BORDERS);
        grid.addComponentColumn(product -> createCard(product));
        grid.addClassName("grid");
        grid.setWidth("30%");
        grid.setHeight("50%");

        if (Cart.getProducts() != null) {
            grid.setItems(Cart.getProducts());
        }

        confirmButton.addClickListener(click -> {
            List<Product> products = new ArrayList<>();
            if (!Cart.getPizzas().isEmpty()) {
                order.setDrinks(Cart.getDrinks());
                order.setDesserts(Cart.getDesserts());
                order.setPizzas(Cart.getPizzas());
                order.setCustomer(Cart.getCustomer());
                order.setAddress(Cart.getAddress());
                order.setEmployee(service.findEmployee(Cart.getAddress().getPostalCode()));

                System.out.println("!!" + Cart.getAddress().getPostalCode());

                service.saveOrder(order);
            }
            else {
                confirmButton.setText("Order requires at least one pizza!");
            }
        });

        add(confirmationHeader, grid, confirmButton);
    }

    private HorizontalLayout createCard(Product p) {
        HorizontalLayout card = new HorizontalLayout();
        card.addClassName("card");
        card.setSpacing(false);
        card.getThemeList().add("spacing-s");

        VerticalLayout description = new VerticalLayout();
        description.addClassName("description");
        description.setSpacing(false);
        description.setPadding(false);

        HorizontalLayout header = new HorizontalLayout();
        header.addClassName("header");
        header.setSpacing(false);
        header.getThemeList().add("spacing-s");

        Span name = new Span(p.getName());
        name.addClassName("name");
        header.add(name);

        Span price = new Span(String.valueOf(p.getPriceString()));
        price.addClassName("price");

        description.add(header);
        card.add(description, price);
        return card;
    }
}
