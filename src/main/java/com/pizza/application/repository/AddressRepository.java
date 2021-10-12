package com.pizza.application.repository;

import com.pizza.application.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query("select a from Address a " +
            "where lower(a.streetName) = lower(:streetName) " +
            "and lower(a.postalCode) = lower(:postalCode) " +
            "and lower(a.houseNumber) = lower(:houseNumber)")
    Address search(@Param("streetName") String streetName,
                   @Param("postalCode") String postalCode,
                   @Param("houseNumber") String houseNumber);

}