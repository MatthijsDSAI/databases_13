package com.pizza.application.views.ordered;

import com.pizza.application.entity.CustomerOrder;
import com.pizza.application.service.PizzaService;
import com.pizza.application.util.Cart;
import com.pizza.application.util.Product;
import com.pizza.application.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.Command;

import java.util.Timer;
import java.util.TimerTask;

@PageTitle("Order placed")
@Route(value = "order", layout = MainLayout.class)
public class OrderedView extends VerticalLayout implements AfterNavigationObserver {

    Grid<Product> grid = new Grid<>();
    CustomerOrder order;

    public OrderedView(PizzaService service) {
        setSpacing(true);
        setPadding(true);
        addClassName("cart-view");
        setWidthFull();
        setHeightFull();

        HorizontalLayout overview = new HorizontalLayout();
        overview.setHeight("100%");
        overview.setWidth("100%");

        H3 orderedHeader = new H3("Thank you for your order!");
        add(orderedHeader);

        if (Cart.getProducts() != null) {
            grid.setItems(Cart.getCopyProducts());
        }

        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_NO_ROW_BORDERS);
        grid.addComponentColumn(product -> createCard(product));
        grid.addClassName("grid");
        grid.setHeight("50%");
        grid.setWidth("30%");

        H4 status = new H4("Order status: in process");

        Button cancelButton = new Button("Cancel order");
        cancelButton.addClickListener(click -> {
            service.deleteOrder(order);
            cancelButton.setText("Order cancelled");
        });

        overview.add(grid, status);
        add(overview, cancelButton);
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

    @Override
    public void afterNavigation(AfterNavigationEvent afterNavigationEvent) {
        order = Cart.getOrder();
    }
}
