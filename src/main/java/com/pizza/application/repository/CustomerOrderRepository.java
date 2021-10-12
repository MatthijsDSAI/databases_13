package com.pizza.application.repository;

import com.pizza.application.entity.Address;
import com.pizza.application.entity.Customer;
import com.pizza.application.entity.CustomerOrder;
import com.pizza.application.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long> {

}