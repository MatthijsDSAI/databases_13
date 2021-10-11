package com.pizza.application;

import com.pizza.application.entity.Customer;
import com.pizza.application.entity.CustomerOrder;
import com.pizza.application.repository.CustomerRepository;
import com.pizza.application.repository.PizzaRepository;
import com.pizza.application.entity.Pizza;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.vaadin.artur.helpers.LaunchUtil;
import com.vaadin.flow.component.dependency.NpmPackage;

import java.util.Set;

/**
 * The entry point of the Spring Boot application.
 */
@SpringBootApplication
@NpmPackage(value = "lumo-css-framework", version = "^4.0.10")
@NpmPackage(value = "line-awesome", version = "1.3.0")
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        LaunchUtil.launchBrowserInDevelopmentMode(SpringApplication.run(Application.class, args));
    }

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository) {
        return args -> {
            Customer jeff = new Customer("Jeff", "Bezos", "9999");
            customerRepository.save(jeff);
        };
    }
}
