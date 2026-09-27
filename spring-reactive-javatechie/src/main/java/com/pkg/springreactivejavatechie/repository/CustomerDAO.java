package com.pkg.springreactivejavatechie.repository;

import com.pkg.springreactivejavatechie.model.Customer;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class CustomerDAO {

    public List<Customer> getCustomers()  {
        return IntStream.rangeClosed(1, 10)
                .peek(CustomerDAO::sleepExecution)
                .peek(i -> System.out.println("processing count : " + i))
                .mapToObj(i -> new Customer(i, "customer " + i))
                .collect(Collectors.toList());
    }
/*
    In Java Streams, the peek() method is a debugging and intermediate operation that lets you look at the elements as they flow through the stream pipeline without modifying them.

            🔑 Purpose of peek()
    Observation: It’s mainly used to inspect elements (like printing them) during stream processing.

    Non-terminal: It doesn’t trigger execution by itself; only a terminal operation (forEach, collect, etc.) will cause peek() to run.

    Side-effects only: It should not be used to change data, just to observe.

            Stream.of("A", "B", "C")
            .peek(s -> System.out.println("Before map: " + s))
            .map(String::toLowerCase)
    .peek(s -> System.out.println("After map: " + s))
            .forEach(System.out::println);

    Before map: A
    After map: a
            a
    Before map: B
    After map: b
            b
    Before map: C
    After map: c
            c
*/

    private static void sleepExecution(int i){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Flux<Customer> getCustomersStream()  {
        return Flux.range(1,10)
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(i -> System.out.println("processing count in stream flow : " + i))
                .map(i -> new Customer(i, "customer" + i));
    }
}
