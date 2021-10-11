package com.pizza.application.views.cart;

import com.pizza.application.entity.Cart;
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
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Shopping cart")
@Route(value = "cart", layout = MainLayout.class)
public class CartView extends HorizontalLayout implements AfterNavigationObserver {

    Grid<Product> grid = new Grid<>();

    public CartView() {

        this.setAlignItems(Alignment.AUTO);
        this.setSpacing(true);
        this.setPadding(true);
        this.addClassName("cart-view");
        this.setWidthFull();
        this.setHeightFull();

        VerticalLayout paymentLayout = new VerticalLayout();
        paymentLayout.setAlignItems(Alignment.START);
        H3 checkoutHeader = new H3("Payment");
        paymentLayout.add(checkoutHeader);

        HorizontalLayout nameLayout = new HorizontalLayout();
        TextField firstName = new TextField("First name");
        TextField lastName = new TextField("Last name");
        nameLayout.add(firstName);
        nameLayout.add(lastName);

        NumberField phoneField = new NumberField("Phone number");
        phoneField.setWidth("300px");

        HorizontalLayout addressLayout = new HorizontalLayout();
        TextField addressField = new TextField("Street name");
        addressField.setWidth("300px");
        NumberField houseNumField = new NumberField("Number");
        houseNumField.setWidth("80px");
        addressLayout.add(addressField, houseNumField);

        NumberField postField = new NumberField("Postal code");

        Button orderButton = new Button("Confirm order");
        orderButton.addClickListener(click -> {

        });

        paymentLayout.add(nameLayout, phoneField, addressLayout, postField, orderButton);

        VerticalLayout cartLayout = new VerticalLayout();
        H3 cartHeader = new H3("Order");
        cartLayout.setAlignItems(Alignment.START);
        cartLayout.setWidth("40%");

        H4 priceHeader = new H4();
        priceHeader.setText("Total: " + Cart.getTotalPrice());

        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_NO_ROW_BORDERS);
        grid.addComponentColumn(product -> createCard(product));
        grid.addClassName("grid");

        Button emptyButton = new Button("Empty cart");
        emptyButton.addClickListener(click -> {
            Cart.emptyCart();
            grid.removeAllColumns();
            priceHeader.setText("Total: " + Cart.getTotalPrice());
        });
        cartLayout.add(cartHeader, grid, priceHeader, emptyButton);

        this.add(cartLayout, paymentLayout);
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

//        Span price = new Span(String.valueOf(p.getPriceString()));
//        price.addClassName("price");

        description.add(header);
        card.add(description);
        return card;
    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {
        if (Cart.getProducts() != null) {
            grid.setItems(Cart.getProducts());
        }
    }

}
