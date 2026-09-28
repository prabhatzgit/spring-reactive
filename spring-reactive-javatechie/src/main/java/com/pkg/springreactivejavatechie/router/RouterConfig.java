package com.pkg.springreactivejavatechie.router;

import com.pkg.springreactivejavatechie.handler.CustomerHandler;
import com.pkg.springreactivejavatechie.handler.CustomerStreamHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig {

    private CustomerHandler customerHandler;

    private CustomerStreamHandler customerStreamHandler;

    public RouterConfig(CustomerHandler customerHandler, CustomerStreamHandler customerStreamHandler){
        this.customerHandler = customerHandler;
        this.customerStreamHandler = customerStreamHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> routerFunction(){
        return RouterFunctions.route()
                // Here able to write N number of http request
                .GET("/router/customers", customerHandler::loadCustomers)
                .GET("/router/customers/reactive-streams", customerStreamHandler::getCustomersStream)
                // path variable
                .GET("/router/customers/{input}", customerHandler::findCustomer)
                .POST("/router/customer/save", customerHandler::saveCustomer)
                .build();
    }
}
