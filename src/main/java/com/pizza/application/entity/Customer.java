package com.pizza.application.entity;

import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;
import java.util.Set;

@Table(name = "customer")
@Entity(name = "customer")
public class Customer {

    @Id
    @Column(name = "customer_id", nullable = false, updatable = false)
    @SequenceGenerator(name = "customer_sequence", sequenceName = "customer_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_sequence")
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

//    @OneToMany(mappedBy = "customer")
//    private Set<CustomerOrder> orders;
//
//    @ManyToOne
//    @JoinColumn(name = "address_id", nullable = false)
//    private Address address;

//    public Customer(String firstName, String lastName, String phoneNumber, Set<CustomerOrder> orders, Address address) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.phoneNumber = phoneNumber;
//        this.orders = orders;
//        this.address = address;
//    }

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Customer(String firstName, String lastName, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public Customer() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

//    public Set<CustomerOrder> getOrders() {
//        return orders;
//    }
//
//    public void setOrders(Set<CustomerOrder> orders) {
//        this.orders = orders;
//    }
//
//    public Address getAddress() {
//        return address;
//    }
//
//    public void setAddress(Address address) {
//        this.address = address;
//    }
//
//    @Override
//    public String toString() {
//        return "Customer{" +
//                "id=" + id +
//                ", firstName='" + firstName + '\'' +
//                ", lastName='" + lastName + '\'' +
//                ", phoneNumber='" + phoneNumber + '\'' +
//                ", orders=" + orders +
//                ", address=" + address +
//                '}';
//    }


    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}