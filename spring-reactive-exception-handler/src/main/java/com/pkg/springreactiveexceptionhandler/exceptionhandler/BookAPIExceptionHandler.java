package com.pkg.springreactiveexceptionhandler.exceptionhandler;

import org.springframework.boot.autoconfigure.web.WebProperties;

import org.springframework.boot.web.error.ErrorAttributeOptions;

import org.springframework.boot.webflux.autoconfigure.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.webflux.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
public class BookAPIExceptionHandler extends AbstractErrorWebExceptionHandler {

    public BookAPIExceptionHandler(ErrorAttributes errorAttributes, WebProperties.Resources resources,
                                   ApplicationContext applicationContext, ServerCodecConfigurer configurer) {
        super(errorAttributes, resources, applicationContext);
        // Defined own reader and writer
        this.setMessageReaders(configurer.getReaders());
        this.setMessageWriters(configurer.getWriters());
    }

    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        // For single endpoint
        // return RouterFunctions.route(RequestPredicates.GET, this::renderException).build();
        // custom handler exception mechanism for all endpoints
        return RouterFunctions.route(RequestPredicates.all(),this::renderException);
    }

    private Mono<ServerResponse> renderException(ServerRequest request) {
        Map<String, Object> error = this.getErrorAttributes(request, ErrorAttributeOptions.defaults());
        /*To remove specific attributes*/
        // error.remove("status");
        // error.remove("requestId");
        return ServerResponse.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(error));

    }
}