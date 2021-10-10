package com.pizza.application.views.account;

import com.pizza.application.views.MainLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Account")
@Route(value = "account", layout = MainLayout.class)
public class AccountView extends VerticalLayout {

    public AccountView() {

    }
}
