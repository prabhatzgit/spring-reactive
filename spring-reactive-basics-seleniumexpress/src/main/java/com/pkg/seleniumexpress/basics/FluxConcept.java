package com.pkg.seleniumexpress.basics;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.function.Consumer;

public class FluxConcept {

    // Publisher: publish or emit data(many data with all kind of datatypes)
    Flux<String> fooPublisher(){
        // In order to create a flux, use just()
        // returns flux of strings
        Flux<String> publisher = Flux.just("abhi","ramya","ramya").delayElements(
                Duration.ofSeconds(1)
        );
        return publisher;
    }

    public static void main(String[] args) throws InterruptedException {
        Flux<String> publisher = new FluxConcept().fooPublisher();
        // To get or read data from publisher, use subscribe()
        /*publisher.subscribe(new Consumer<String>() {
            @Override
            public void accept(String string) {
                System.out.println(string);
            }
        });*/
        publisher.subscribe(string -> System.out.println(string));
        Thread.sleep(100000);

    }
    /* In this above method, threads wait one second and publishes the data
    * So, subscriber get data from publisher after one second
    * once subscriber gets first data main thread says subscriber is subscribed
    * with this publisher and whenever this publisher emit data after one second
    * Publisher push data to subscriber and subscriber is going to consume the data
    * publisher.subscribe(string -> System.out.println(string))
    * */

    /*onSubscribe() : handled by main thread reactor-http-nio-2
    * request(): handled by main thread reactor-http-nio-2
    * onNext(): handled by parallel thread*/
}
