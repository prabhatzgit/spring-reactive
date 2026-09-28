package com.pkg.springreactivejavatechie.handler;

import com.pkg.springreactivejavatechie.model.Customer;
import com.pkg.springreactivejavatechie.repository.CustomerDAO;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerStreamHandler {

    private CustomerDAO customerDAO;

    public CustomerStreamHandler(CustomerDAO customerDAO){
        this.customerDAO = customerDAO;
    }

    public Mono<ServerResponse> getCustomersStream(ServerRequest serverRequest){
            Flux<Customer> customerStream = customerDAO.getCustomersStream();
            return ServerResponse.ok()
                    // if we skip the contentType(MediaType.TEXT_EVENT_STREAM), then data send as an object
                    // If we use it, then response show as an event or stream
                    .contentType(MediaType.TEXT_EVENT_STREAM)
                    .body(customerStream, Customer.class);
    }
}
