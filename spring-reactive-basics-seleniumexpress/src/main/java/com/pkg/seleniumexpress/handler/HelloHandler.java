package com.pkg.seleniumexpress.handler;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Component
public class HelloHandler {

    public Mono<ServerResponse> hellohandler() {

        Flux<String> dataPublisher = Flux.just("hello","world","Prabhat Learns Spring Reactive by Selenium Express","learning","spring-reactive");
        Mono<ServerResponse> serverResponse = ServerResponse
                .ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(dataPublisher,String.class);
        return serverResponse;
    }

    // functional style of coding
    public Mono<ServerResponse> helloHandlerPathVariable(ServerRequest serverRequest) {

        String pathVariableName = serverRequest.pathVariable("yourName");
        String serverResponse = "Your name is : " + pathVariableName;
        // this string response should return as Mono<ServerResponse>
        Mono<String> publisher = Mono.just(serverResponse);
        // this serverResponse needs to create in terms of Mono<ServerResponse> else it returns
        // as string and show exception -> IllegalArgumentException
        // producer type is unknown to ReactiveAdapterRegistry
        Mono<ServerResponse> monoResponse = ServerResponse.ok().body(publisher, String.class);
        return monoResponse;
    }

    public Mono<ServerResponse> hiHandler(ServerRequest serverRequest) {
        Flux<String> dataPublisher = Flux.just("Prabhat","Ranjan","Mahanty").delayElements(Duration.ofSeconds(2));
        return ServerResponse
                .ok()
                //.contentType(MediaType.TEXT_EVENT_STREAM)
                .body(dataPublisher,String.class);
    }
}