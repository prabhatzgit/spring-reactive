package com.pkg.springreactivejavatechie.handler;

import com.pkg.springreactivejavatechie.model.Customer;
import com.pkg.springreactivejavatechie.repository.CustomerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerHandler {

    @Autowired
    private CustomerDAO customerDAO;

    // ServerRequest is a predefined class which has all metadata
    // ServerResponse helps to map all the response metadata.
    public Mono<ServerResponse> loadCustomers(ServerRequest serverRequest){
        Flux<Customer> customerList = customerDAO.getCustomersListUsingRouterAndHandler();
        return ServerResponse.ok().body(customerList, Customer.class);
    }

    public Mono<ServerResponse> findCustomer(ServerRequest request){
        int customerId= Integer.valueOf( request.pathVariable("input"));
        // dao.getCustomerList().filter(c->c.getId()==customerId).take(1).single();
        Mono<Customer> customerMono = customerDAO.getCustomersListUsingRouterAndHandler().filter(c -> c.getId() == customerId).next();
        return ServerResponse.ok().body(customerMono,Customer.class);
    }

    public Mono<ServerResponse> saveCustomer(ServerRequest request){
        Mono<Customer> customerMono = request.bodyToMono(Customer.class);
        Mono<String> saveResponse = customerMono.map(dto -> dto.getId() + ":" + dto.getName());
        return ServerResponse.ok().body(saveResponse,String.class);
    }
}
