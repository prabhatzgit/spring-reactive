package com.pkg.springreactivejavatechie.controller;


import com.pkg.springreactivejavatechie.model.Customer;
import com.pkg.springreactivejavatechie.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    // Traditional REST approach
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.loadAllCustomers();
    }

    // Spring Reactive approach
    @GetMapping(value = "/reactive-stream",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Customer> getAllCustomersStream() {
        return customerService.loadAllCustomersStream();
    }
    /*
    * In this reactive programming, we need to send the response as a event stream. So, enable
    * the MediaType as a TEXT_EVENT_STREAM_VALUE */
}