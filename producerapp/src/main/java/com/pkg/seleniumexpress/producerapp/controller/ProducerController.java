package com.pkg.seleniumexpress.producerapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {

    @GetMapping("/hello")
    public String getData() throws InterruptedException {
        // Case1: sleep for 10 ms
        Thread.sleep(10000);
        return "Hello World!";
    }
}