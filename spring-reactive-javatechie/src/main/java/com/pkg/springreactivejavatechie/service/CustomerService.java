package com.pkg.springreactivejavatechie.service;

import com.pkg.springreactivejavatechie.model.Customer;
import com.pkg.springreactivejavatechie.repository.CustomerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerDAO customerDAO;


    public List<Customer> loadAllCustomers() {
        long start = System.currentTimeMillis();
        List<Customer> customers = customerDAO.getCustomers();
        long end = System.currentTimeMillis();
        System.out.println("Total execution time : " + (end - start));
        return customers;
    }

    public Flux<Customer> loadAllCustomersStream() {
        long start = System.currentTimeMillis();
        Flux<Customer> customers = customerDAO.getCustomersStream();
        long end = System.currentTimeMillis();
        System.out.println("Total execution time : " + (end - start));
        return customers;
    }
}
