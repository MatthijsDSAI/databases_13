package com.pizza.application.service;

import com.pizza.application.entity.*;
import com.pizza.application.repository.*;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {

    private final DrinkRepository drinkRepository;
    private final PizzaRepository pizzaRepository;
    private final DessertRepository dessertRepository;
    private final AddressRepository addressRepository;
    private final CustomerRepository customerRepository;
    private final CustomerOrderRepository customerOrderRepository;
    private final EmployeeRepository employeeRepository;

    public PizzaService(DrinkRepository drinkRepository,
                        PizzaRepository pizzaRepository,
                        DessertRepository dessertRepository,
                        AddressRepository addressRepository,
                        CustomerRepository customerRepository,
                        CustomerOrderRepository customerOrderRepository,
                        EmployeeRepository employeeRepository) {
        this.drinkRepository = drinkRepository;
        this.pizzaRepository = pizzaRepository;
        this.dessertRepository = dessertRepository;
        this.addressRepository = addressRepository;
        this.customerRepository = customerRepository;
        this.customerOrderRepository = customerOrderRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<Drink> findAllDrinks() {
        return drinkRepository.findAll();
    }

    public List<Pizza> findAllPizzas() {
        return pizzaRepository.findAll();
    }

    public List<Dessert> findAllDesserts() {
        return dessertRepository.findAll();
    }

    public boolean addressExists(String streetName, String postalCode, String houseNumber) {
        return (addressRepository.search(streetName, postalCode, houseNumber) != null);
    }

    public Address findAddress(String streetName, String postalCode, String houseNumber) {
        return addressRepository.search(streetName, postalCode, houseNumber);
    }

    public Address saveAddress(Address address) {
        if (address == null) {
            System.err.println("address is null.");
            return null;
        }
        return addressRepository.save(address);
    }

    public Customer saveCustomer(Customer customer) {
        if (customer == null) {
            System.err.println("Customer is null.");
            return null;
        }
        return customerRepository.save(customer);
    }

    public CustomerOrder saveOrder(CustomerOrder order) {
        if (order == null) {
            System.err.println("Customer is null.");
            return null;
        }
        return customerOrderRepository.save(order);
    }

    public void deleteOrder(CustomerOrder order) {
        customerOrderRepository.delete(order);
    }

    public Employee findEmployee(String postalCode) {
        return employeeRepository.search(postalCode);
    }

}
