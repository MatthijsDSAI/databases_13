package com.pizza.application.views.cart;

import com.pizza.application.entity.Address;
import com.pizza.application.entity.Customer;
import com.pizza.application.entity.CustomerOrder;
import com.pizza.application.repository.AddressRepository;
import com.pizza.application.service.PizzaService;
import com.pizza.application.util.Cart;
import com.pizza.application.util.Product;
import com.pizza.application.views.MainLayout;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.crud.Crud;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.PropertyId;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@PageTitle("Shopping cart")
@Route(value = "cart", layout = MainLayout.class)
public class CartView extends HorizontalLayout implements AfterNavigationObserver, Serializable {

    private static final long serialVersionUID = 1;
    private Customer customer = new Customer();
    Grid<Product> grid = new Grid<>();
    Binder<Customer> binder = new BeanValidationBinder<>(Customer.class);
    @PropertyId("firstName")
    TextField firstName = new TextField("First name");
    @PropertyId("lastName")
    TextField lastName = new TextField("Last name");
    TextField addressField = new TextField("Street name");
    TextField postField = new TextField("Postal code");
    TextField houseNumField = new TextField("Number");
    @PropertyId("phoneNumber")
    TextField phoneNumber = new TextField("Phone number");
    @PropertyId("address")
    Address address;

    private PizzaService service;

    public CartView(PizzaService service) {
        this.service = service;

        binder.bindInstanceFields(this);
        binder.setBean(customer);

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
        nameLayout.add(firstName);
        nameLayout.add(lastName);

        phoneNumber.setWidth("300px");

        HorizontalLayout addressLayout = new HorizontalLayout();
        addressField.setWidth("300px");
        houseNumField.setWidth("80px");
        addressLayout.add(addressField, houseNumField);

        Button orderButton = new Button("Save information");
        orderButton.addClickListener(click -> {
            boolean addressExists = service.addressExists(addressField.getValue(), postField.getValue(), houseNumField.getValue());
            if(addressExists) {
                address = service.findAddress(addressField.getValue(), postField.getValue(), houseNumField.getValue());
                customer.setAddress(address);
                Cart.setAddress(address);
                validateAndSave();
            }
            else {
                address = service.saveAddress(new Address(postField.getValue(), addressField.getValue(), houseNumField.getValue()));
                customer.setAddress(address);
                Cart.setAddress(address);
                validateAndSave();
            }

            UI.getCurrent().navigate("confirm-order");
        });

        paymentLayout.add(nameLayout, phoneNumber, addressLayout, postField, orderButton);

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
            priceHeader.setText(
                    "Total (inc. 9% VAT): " + Cart.getTotalPriceWithVAT()
            );
        });
        cartLayout.add(cartHeader, grid, priceHeader, emptyButton);

        binder.addStatusChangeListener(e -> orderButton.setEnabled(binder.isValid()));

        binder.getBean();

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

        Span price = new Span(String.valueOf(p.getPriceString()));
        price.addClassName("price");

        description.add(header);
        card.add(description, price);
        return card;
    }

    private void validateAndSave() {
        try {
            binder.writeBean(customer);
            Cart.setCustomer(service.saveCustomer(customer));
            customer = new Customer();
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    public static abstract class ContactFormEvent extends ComponentEvent<CartView> {
        private Customer customer;

        protected ContactFormEvent(CartView source, Customer customer) {
            super(source, false);
            this.customer = customer;
        }

        public Customer getCustomer() {
            return customer;
        }
    }

    public static class SaveEvent extends ContactFormEvent {
        SaveEvent(CartView source, Customer customer) { super(source, customer); }
    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {
        if (Cart.getProducts() != null) {
            grid.setItems(Cart.getProducts());
        }
    }

}
