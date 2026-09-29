package com.pkg.seleniumexpress.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@RestController
public class HelloWorldController {

    // 1. when we use spring-starter-webflux dependency, netty server started in
    // @RestController has built-in @ResponseBody. else we need to assign @ResponseBody at method level
    // Case1: It provides a list of string
    @GetMapping("/hello")
    // @ResponseBody
    public List<String> helloWorldController(){
        List<String> listOfString = List.of("hello","world","hi","everyone");
        return listOfString;
    }

    /*
    * If some db operations or heavy operation is going on which takes more time to process the request
    * Here: @GetMapping("/understanding-drawback-of-blocking-call")-> accepts request
    * handler method: understandingDrawbackOfBlockingCall() which handles the request /understanding-drawback-of-blocking-call
    * whenever the request match -> this understandingDrawbackOfBlockingCall() is invoked
    * Whenever it invoked, imagine long-running task is going on
    * http://localhost:8080/hello The thread which handles the request is blocked for 4s
    * and entire list is ready, it returns complete list by adding in all the elements to the list
    * Blocking call: The thread which handles the request, that thread should send response
    * during this execution, if some long-running task going on, then thread should wait and it is blocked
    * */
    @GetMapping("/understanding-drawback-of-blocking-call")
    public List<String> understandingDrawbackOfBlockingCall() throws InterruptedException {
        List<String> listOfString = List.of("hello","world","hi","everyone");
        ArrayList<String> returnList = new ArrayList<>();
        // long-running task which adds element from listOfString to a new list returnList
        for(String str : listOfString){
            // can check whichever thread is able to process the elements
            // reactor-http-nio-3 has processed all these elements and we can check the console
            System.out.println("Element " + str + " processed by thread : - " + Thread.currentThread().getName());
            returnList.add(str); // add iterated string to returnList and wait for is
            // It will block 1 ms while adding each element
            // If we do db operations by multiple users, and it might be provided OutOfMemoryError
            // this is a blocking call
            Thread.sleep(2000);
        }
        return returnList;
    }

    // Case3: go with reactive
    @GetMapping("/benefits-of-asynchronous-call-using-flux")
    public Flux<String> benefitsOfSpringReactiveUsingFlux() throws InterruptedException {

        List<String> listOfString = List.of("hello","world","hi","everyone");
        // Flux is a publisher and helps to create reactive programming
        // Flux publishes zero to many data and helps non-blocking operations
        // Publisher publish data in async way and it returns here Flux<String> like "helloworldhieveryone"
        Flux<String> publisher = Flux.fromIterable(listOfString).delayElements(Duration.ofSeconds(2)).log();
        // In the above code, the client or subscriber means here postman consumes the data from above publisher in
        // each interval of 2 seconds

        // But if we consider above synchronous api or scenario, there it wait the sleep() time period
        // and then it gives data to client

        // the parallel threads which publish the data from stream are managed by netty server
        return publisher;

        // asynchronous and non-blocking: fetching and able to data immediately on console. It will not wait other thread's operations or entire
        // request get processed or server get the entire response.
        // in each 2s we get one one data from publisher hello-> 2s-> world -> 2s -> hi -> 2s -> everyone -> "helloworldhieveryone"
    }

    @GetMapping("/benefits-of-asynchronous-call-using-mono")
    public Mono<String> benefitsOfSpringReactiveUsingMono() throws InterruptedException {

        // Mono publishes only single element or object from stream
        return Mono.just("Prabhat").log();
    }
}