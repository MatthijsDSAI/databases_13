package com.pizza.application.repository;

import com.pizza.application.entity.Dessert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DessertRepository extends JpaRepository<Dessert, Long> {
}