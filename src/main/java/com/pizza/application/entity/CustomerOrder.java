package com.pizza.application.entity;

import com.pizza.application.util.Product;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Table(name = "customer_order")
@Entity(name = "customer_order")
public class CustomerOrder {

    @Id
    @Column(name = "order_id", nullable = false, updatable = false)
    @SequenceGenerator(name = "order_sequence", sequenceName = "order_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_sequence")
    private Long order_id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "customer_order_pizza", joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "pizza_id"))
    List<Pizza> pizzas;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "customer_order_drink", joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "drink_id"))
    List<Drink> drinks;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "customer_order_dessert", joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "dessert_id"))
    List<Dessert> desserts;

    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    private Address address;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    public void setDrinks(List<Drink> drinks) {
        this.drinks = drinks;
    }

    public void setDesserts(List<Dessert> desserts) {
        this.desserts = desserts;
    }
}