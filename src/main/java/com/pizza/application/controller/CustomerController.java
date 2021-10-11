package com.pizza.application.controller;

import com.pizza.application.entity.Customer;
import com.pizza.application.exception.ResourceNotFoundException;
import com.pizza.application.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    // get customer rest API
    @GetMapping("customers") // to create http request
    public List<Customer> getAllCustomer() {
        return this.customerRepository.findAll();
    }
    // get customer by id
    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable(value = "id") Long customerId)
            throws ResourceNotFoundException {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer with id: " + customerId + " not found :("));
        return ResponseEntity.ok().body(customer);
    }


    // save customer
    @PostMapping("customers") // create recieve entity
    public Customer createCustomer(@RequestBody Customer customer) {
        return this.customerRepository.save(customer);
    }
/*
    // udpate employee
    @PutMapping("customers/{id}")
    public ResponseEntity<Customer> updateCustomer(
            @PathVariable(value="id") Long customerId, //@Valid
            @RequestBody Customer customerDetails) throws ResourceNotFoundException
    { //request body converts json to java object
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer with id: " + customerId + " not found :("));
        customer.setEmail(customerDetails.getEmail()); // updates customer object
        customer.setPhoneNumber(customerDetails.getPhoneNumber());
        customer.setFirstName(customerDetails.getFirstName());

        return ResponseEntity.ok(this.customerRepository.save(customer)); // returns new updated employee object
    }
    */


    /*
    // delete employee
    @DeleteMapping("/customers/{id}")
    public Map<String, Boolean> deleteCustomer(@PathVariable(value = "id") Long customerId)
            throws ResourceNotFoundException {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer with id: " + customerId + " not found :("));
        this.customerRepository.delete(customer); //might not need to use this
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
    */

}
