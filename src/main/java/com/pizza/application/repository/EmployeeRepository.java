package com.pizza.application.repository;

import com.pizza.application.entity.Address;
import com.pizza.application.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("select e from Employee e " +
            "where lower(e.postalCode) = lower(:postalCode)")
    Employee search(@Param("postalCode") String postalCode);

}