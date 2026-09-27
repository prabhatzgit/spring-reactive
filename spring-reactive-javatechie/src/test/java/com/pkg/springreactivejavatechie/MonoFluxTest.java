package com.pkg.springreactivejavatechie;


import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MonoFluxTest {

    @Test
    public void testMono(){
        // Mono accepts a single argument.
        Mono<String> monoString = Mono.just("javatechie");
        // Mono is from reactor.core.publisher. So, Mono and Flux acts as a Publisher
        // So, to access any Publisher, we need to call subscriber() of Subscriber.
        monoString.subscribe();
        // So, to print the published event
        monoString.subscribe(System.out::println);
        // When publisher used subscribe(). it starts immediately to emit the event
    }

    @Test
    public void testMonoVerifyWorkFlow(){
        // Check whether Mono and Flux works reactive work flow or not, use log().
        // So that each of process execution printed on console.
        Mono<String> monoString = Mono.just("javatechie").log();
        monoString.subscribe();
        monoString.subscribe(System.out::println);
    }

    @Test
    public void testMonoOnErrorWorkFlow(){
        Mono<?> monoString = Mono.just("javatechie")
                        .then(Mono.error(new RuntimeException("Exception occurred")))
                .log();
        monoString.subscribe();
        monoString.subscribe(System.out::println,(e)->System.out.println(e.getMessage()));
    }

    // To receive N number of event, we use Flux
    @Test
    public void testFlux(){
        Flux<String> fluxString = Flux.just("Java17","Spring Boot","Microservice","Spring Reactive","Kafka").log();
        fluxString.subscribe(System.out::println);
    }

/*
    "C:\Program Files\Java\jdk-17\bin\java.exe" -javaagent:C:\Users\mahan\AppData\Local\JetBrains\IntelliJIdea2026.2\captureAgent\debugger-agent.jar=file:///C:/Users/mahan/AppData/Local/Temp/capture1088235810675189771.props -ea -Didea.test.cyclic.buffer.size=1048576 "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2024.2.1\lib\idea_rt.jar=64679" -Dkotlinx.coroutines.debug.enable.creation.stack.trace=false -Ddebugger.agent.enable.coroutines=true -Dkotlinx.coroutines.debug.enable.flows.stack.trace=true -Dkotlinx.coroutines.debug.enable.mutable.state.flows.stack.trace=true -Ddebugger.async.stack.trace.for.all.threads=true -Ddebugger.agent.enable.log.capture=true -Dfile.encoding=UTF-8 -classpath "C:\Users\mahan\.m2\repository\org\junit\platform\junit-platform-launcher\6.0.3\junit-platform-launcher-6.0.3.jar;C:\Users\mahan\.m2\repository\org\jspecify\jspecify\1.0.0\jspecify-1.0.0.jar;C:\Program Files\JetBrains\IntelliJ IDEA 2024.2.1\lib\idea_rt.jar;C:\Program Files\JetBrains\IntelliJ IDEA 2024.2.1\plugins\junit\lib\junit6-rt.jar;C:\Program Files\JetBrains\IntelliJ IDEA 2024.2.1\plugins\junit\lib\junit5-rt.jar;C:\Program Files\JetBrains\IntelliJ IDEA 2024.2.1\plugins\junit\lib\junit-rt.jar;C:\prabhat\springboot_workspace\spring-reactive\spring-reactive-javatechie\target\test-classes;C:\prabhat\springboot_workspace\spring-reactive\spring-reactive-javatechie\target\classes;C:\Users\mahan\.m2\repository\org\springframework\boot\spring-boot-starter-webflux\4.1.1\spring-boot-starter-webflux-4.1.1.jar;C:\Users\mahan\.m2\repository\org\springframework\boot\spring-boot-starter\4.1.1\spring-boot-starter-4.1.1.jar;C:\Users\mahan\.m2\repository\org\springframework\boot\spring-boot-starter-logging\4.1.1\spring-boot-starter-logging-4.1.1.jar;C:\Users\mahan\.m2\repository\ch\qos\logback\logback-classic\1.5.38\logback-classic-1.5.38.jar;C:\Users\mahan\.m2\repository\ch\qos\logback\logback-core\1.5.38\logback-core-1.5.38.jar;C:\Users\mahan\.m2\repository\org\apache\logging\log4j\log4j-to-slf4j\2.25.5\log4j-to-slf4j-2.25.5.jar;C:\Users\mahan\.m2\repository\org\apache\logging\log4j\log4j-api\2.25.5\log4j-api-2.25.5.jar;C:\Users\mahan\.m2\repository\org\slf4j\jul-to-slf4j\2.0.18\jul-to-slf4j-2.0.18.jar;C:\Users\mahan\.m2\repository\org\springframework\boot\spring-boot-autoconfigure\4.1.1\spring-boot-autoconfigure-4.1.1.jar;C:\Users\mahan\.m2\repository\jakarta\annotation\jakarta.annotation-api\3.0.0\jakarta.annotation-api-3.0.0.jar;C:\Users\mahan\.m2\repository\org\yaml\snakeyaml\2.6\snakeyaml-2.6.jar;C:\Users\mahan\.m2\repository\org\springframework\boot\spring-boot-starter-jackson\4.1.1\spring-boot-starter-jackson-4.1.1.jar;C:\Users\mahan\.m2\repository\org\springframework\boot\spring-boot-jackson\4.1.1\spring-boot-jackson-4.1.1.jar;C:\Users\mahan\.m2\repository\tools\jackson\core\jackson-databind\3.1.5\jackson-databind-3.1.5.jar;C:\Users\mahan\.m2\repository\com\fasterxml\jackson\core\jackson-annotations\2.21\jackson-annotations-2.21.jar;C:\Users\mahan\.m2\repository\tools\jackson\core\jackson-core\3.1.5\jackson-core-3.1.5.jar;C:\Users\mahan\.m2\repository\org\springframework\boot\spring-boot-starter-reactor-netty\4.1.1\spring-boot-starter-reactor-netty-4.1.1.jar;C:\Users\mahan\.m2\repository\org\springframework\boot\spring-boot-reactor-netty\4.1.1\spring-boot-reactor-netty-4.1.1.jar;C:\Users\mahan\.m2\repository\io\projectreactor\netty\reactor-netty-http\1.3.7\reactor-netty-http-1.3.7.jar;C:\Users\mahan\.m2\repository\io\netty\netty-codec-http\4.2.17.Final\netty-codec-http-4.2.17.Final.jar;C:\Users\mahan\.m2\repository\io\netty\netty-buffer\4.2.17.Final\netty-buffer-4.2.17.Final.jar;C:\Users\mahan\.m2\repository\io\netty\netty-transport\4.2.17.Final\netty-transport-4.2.17.Final.jar;C:\Users\mahan\.m2\repository\io\netty\netty-codec-base\4.2.17.Final\netty-codec-base-4.2.17.Final.jar;C:\Users\mahan\.m2\repository\io\netty\netty-codec-compression\4.2.17.Final\netty-codec-compression-4.2.17.Final.jar;C:\Users\mahan\.m2\repository\io\netty\netty-handler\4.2.17.Final\netty-handler-4.2.17.Final.jar;C:\Users\mahan\.m2\repository\io\netty\netty-codec-http2\4.2.17.Final\netty-codec-http2-4.2.17.Final.jar;C:\Users\mahan\.m2\repository\io\netty\netty-codec-http3\4.2.17.Final\netty-codec-http3-4.2.17.Final.jar;C:\Users\mahan\.m2\repository\io\netty\netty-transport-native-unix-common\4.2.17.Final\netty-transport-native-unix-common-4.2.17.Final.jar;C:\Users\mahan\.m2\repository\io\netty\netty-resolver\4.2.17.Final\netty-resolver-4.2.17.Final.jar;C:\Users\mahan\.m2\repository\io\netty\netty-codec-classes-quic\4.2.17.Final\netty-codec-classes-quic-4.2.17.Final.jar;C:\Users\mahan\.m2\repository\io\netty\netty-codec-native-quic\4.2.17.Final\netty-codec-native-quic-4.2.17.Final-linux-x86_64.jar;C:\Users\mahan\.m2\repository\io\netty\netty-codec-native-quic\4.2.17.Final\netty-codec-native-quic-4.2.17.Final-linux-aarch_64.jar;C:\Users\mahan\.m2\repository\io\netty\netty-codec-native-quic\4.2.17.Final\netty-codec-native-quic-4.2.17.Final-osx-x86_64.jar;C:\Users\mahan\.m2\repository\io\netty\netty-codec-native-quic\4.2.17.Final\netty-codec-native-quic-4.2.17.Final-osx-aarch_64.jar;C:\Users\mahan\.m2\repository\io\netty\netty-codec-native-quic\4.2.17.Final\netty-codec-native-quic-4.2.17.Final-windows-x86_64.jar;C:\Users\mahan\.m2\repository\io\netty\netty-resolver-dns\4.2.17.Final\netty-resolver-dns-4.2.17.Final.jar;C:\Users\mahan\.m2\repository\io\netty\netty-codec-dns\4.2.17.Final\netty-codec-dns-4.2.17.Final.jar;C:\Users\mahan\.m2\repository\io\netty\netty-resolver-dns-native-macos\4.2.17.Final\netty-resolver-dns-native-macos-4.2.17.Final-osx-x86_64.jar;C:\Users\mahan\.m2\repository\io\netty\netty-resolver-dns-classes-macos\4.2.17.Final\netty-resolver-dns-classes-macos-4.2.17.Final.jar;C:\Users\mahan\.m2\repository\io\netty\netty-transport-native-epoll\4.2.17.Final\netty-transport-native-epoll-4.2.17.Final-linux-x86_64.jar;C:\Users\mahan\.m2\repository\io\netty\netty-transport-classes-epoll\4.2.17.Final\netty-transport-classes-epoll-4.2.17.Final.jar;C:\Users\mahan\.m2\repository\io\projectreactor\netty\reactor-netty-core\1.3.7\reactor-netty-core-1.3.7.jar;C:\Users\mahan\.m2\repository\io\netty\netty-handler-proxy\4.2.17.Final\netty-handler-proxy-4.2.17.Final.jar;C:\Users\mahan\.m2\repository\io\netty\netty-codec-socks\4.2.17.Final\netty-codec-socks-4.2.17.Final.jar;C:\Users\mahan\.m2\repository\org\springframework\spring-web\7.0.9\spring-web-7.0.9.jar;C:\Users\mahan\.m2\repository\io\micrometer\micrometer-observation\1.17.1\micrometer-observation-1.17.1.jar;C:\Users\mahan\.m2\repository\io\micrometer\micrometer-commons\1.17.1\micrometer-commons-1.17.1.jar;C:\Users\mahan\.m2\repository\org\springframework\boot\spring-boot-netty\4.1.1\spring-boot-netty-4.1.1.jar;C:\Users\mahan\.m2\repository\io\netty\netty-common\4.2.17.Final\netty-common-4.2.17.Final.jar;C:\Users\mahan\.m2\repository\org\springframework\boot\spring-boot-reactor\4.1.1\spring-boot-reactor-4.1.1.jar;C:\Users\mahan\.m2\repository\org\springframework\boot\spring-boot\4.1.1\spring-boot-4.1.1.jar;C:\Users\mahan\.m2\repository\org\springframework\spring-context\7.0.9\spring-context-7.0.9.jar;C:\Users\mahan\.m2\repository\org\springframework\spring-aop\7.0.9\spring-aop-7.0.9.jar;C:\Users\mahan\.m2\repository\org\springframework\spring-expression\7.0.9\spring-expression-7.0.9.jar;C:\Users\mahan\.m2\repository\io\projectreactor\reactor-core\3.8.7\reactor-core-3.8.7.jar;C:\Users\mahan\.m2\repository\org\reactivestreams\reactive-streams\1.0.4\reactive-streams-1.0.4.jar;C:\Users\mahan\.m2\repository\org\springframework\boot\spring-boot-webflux\4.1.1\spring-boot-webflux-4.1.1.jar;C:\Users\mahan\.m2\repository\org\springframework\boot\spring-boot-http-codec\4.1.1\spring-boot-http-codec-4.1.1.jar;C:\Users\mahan\.m2\repository\org\springframework\spring-webflux\7.0.9\spring-webflux-7.0.9.jar;C:\Users\mahan\.m2\repository\org\springframework\spring-beans\7.0.9\spring-beans-7.0.9.jar;C:\Users\mahan\.m2\repository\org\springframework\boot\spring-boot-web-server\4.1.1\spring-boot-web-server-4.1.1.jar;C:\Users\mahan\.m2\repository\org\springframework\boot\spring-boot-starter-webflux-test\4.1.1\spring-boot-starter-webflux-test-4.1.1.jar;C:\Users\mahan\.m2\repository\org\springframework\boot\spring-boot-starter-jackson-test\4.1.1\spring-boot-starter-jackson-test-4.1.1.jar;C:\Users\mahan\.m2\repository\org\springframework\boot\spring-boot-starter-test\4.1.1\spring-boot-starter-test-4.1.1.jar;C:\Users\mahan\.m2\repository\org\springframework\boot\spring-boot-test\4.1.1\spring-boot-test-4.1.1.jar;C:\Users\mahan\.m2\repository\org\springframework\boot\spring-boot-test-autoconfigure\4.1.1\spring-boot-test-autoconfigure-4.1.1.jar;C:\Users\mahan\.m2\repository\com\jayway\jsonpath\json-path\2.10.0\json-path-2.10.0.jar;C:\Users\mahan\.m2\repository\org\slf4j\slf4j-api\2.0.18\slf4j-api-2.0.18.jar;C:\Users\mahan\.m2\repository\jakarta\xml\bind\jakarta.xml.bind-api\4.0.5\jakarta.xml.bind-api-4.0.5.jar;C:\Users\mahan\.m2\repository\jakarta\activation\jakarta.activation-api\2.1.4\jakarta.activation-api-2.1.4.jar;C:\Users\mahan\.m2\repository\net\minidev\json-smart\2.6.0\json-smart-2.6.0.jar;C:\Users\mahan\.m2\repository\net\minidev\accessors-smart\2.6.0\accessors-smart-2.6.0.jar;C:\Users\mahan\.m2\repository\org\ow2\asm\asm\9.7.1\asm-9.7.1.jar;C:\Users\mahan\.m2\repository\org\assertj\assertj-core\3.27.7\assertj-core-3.27.7.jar;C:\Users\mahan\.m2\repository\net\bytebuddy\byte-buddy\1.18.11\byte-buddy-1.18.11.jar;C:\Users\mahan\.m2\repository\org\awaitility\awaitility\4.3.0\awaitility-4.3.0.jar;C:\Users\mahan\.m2\repository\org\hamcrest\hamcrest\3.0\hamcrest-3.0.jar;C:\Users\mahan\.m2\repository\org\junit\jupiter\junit-jupiter\6.0.3\junit-jupiter-6.0.3.jar;C:\Users\mahan\.m2\repository\org\junit\jupiter\junit-jupiter-api\6.0.3\junit-jupiter-api-6.0.3.jar;C:\Users\mahan\.m2\repository\org\opentest4j\opentest4j\1.3.0\opentest4j-1.3.0.jar;C:\Users\mahan\.m2\repository\org\junit\platform\junit-platform-commons\6.0.3\junit-platform-commons-6.0.3.jar;C:\Users\mahan\.m2\repository\org\apiguardian\apiguardian-api\1.1.2\apiguardian-api-1.1.2.jar;C:\Users\mahan\.m2\repository\org\junit\jupiter\junit-jupiter-params\6.0.3\junit-jupiter-params-6.0.3.jar;C:\Users\mahan\.m2\repository\org\junit\jupiter\junit-jupiter-engine\6.0.3\junit-jupiter-engine-6.0.3.jar;C:\Users\mahan\.m2\repository\org\junit\platform\junit-platform-engine\6.0.3\junit-platform-engine-6.0.3.jar;C:\Users\mahan\.m2\repository\org\mockito\mockito-core\5.23.0\mockito-core-5.23.0.jar;C:\Users\mahan\.m2\repository\net\bytebuddy\byte-buddy-agent\1.18.11\byte-buddy-agent-1.18.11.jar;C:\Users\mahan\.m2\repository\org\objenesis\objenesis\3.3\objenesis-3.3.jar;C:\Users\mahan\.m2\repository\org\mockito\mockito-junit-jupiter\5.23.0\mockito-junit-jupiter-5.23.0.jar;C:\Users\mahan\.m2\repository\org\skyscreamer\jsonassert\1.5.3\jsonassert-1.5.3.jar;C:\Users\mahan\.m2\repository\com\vaadin\external\google\android-json\0.0.20131108.vaadin1\android-json-0.0.20131108.vaadin1.jar;C:\Users\mahan\.m2\repository\org\springframework\spring-core\7.0.9\spring-core-7.0.9.jar;C:\Users\mahan\.m2\repository\commons-logging\commons-logging\1.3.6\commons-logging-1.3.6.jar;C:\Users\mahan\.m2\repository\org\springframework\spring-test\7.0.9\spring-test-7.0.9.jar;C:\Users\mahan\.m2\repository\org\xmlunit\xmlunit-core\2.11.0\xmlunit-core-2.11.0.jar;C:\Users\mahan\.m2\repository\org\springframework\boot\spring-boot-webflux-test\4.1.1\spring-boot-webflux-test-4.1.1.jar;C:\Users\mahan\.m2\repository\org\springframework\boot\spring-boot-webtestclient\4.1.1\spring-boot-webtestclient-4.1.1.jar;C:\Users\mahan\.m2\repository\io\projectreactor\reactor-test\3.8.7\reactor-test-3.8.7.jar;C:\Users\mahan\.m2\repository\org\jspecify\jspecify\1.0.1\jspecify-1.0.1.jar" com.intellij.rt.junit.JUnitStarter -ideVersion5 -junit6 com.pkg.springreactivejavatechie.MonoFluxTest,testFlux
            22:30:40.996 [main] INFO reactor.Flux.Array.1 -- | onSubscribe([Synchronous Fuseable] FluxArray.ArraySubscription)
22:30:41.007 [main] INFO reactor.Flux.Array.1 -- | request(unbounded)
22:30:41.008 [main] INFO reactor.Flux.Array.1 -- | onNext(Java17)
    Java17
22:30:41.009 [main] INFO reactor.Flux.Array.1 -- | onNext(Spring Boot)
    Spring Boot
22:30:41.010 [main] INFO reactor.Flux.Array.1 -- | onNext(Microservice)
    Microservice
22:30:41.010 [main] INFO reactor.Flux.Array.1 -- | onNext(Spring Reactive)
    Spring Reactive
22:30:41.011 [main] INFO reactor.Flux.Array.1 -- | onNext(Kafka)
    Kafka
22:30:41.012 [main] INFO reactor.Flux.Array.1 -- | onComplete()

    Process finished with exit code 0
*/

    @Test
    public void testFluxConcat(){
        Flux<String> fluxString = Flux.just("Java17","Spring Boot","Microservice","Spring Reactive","Kafka")
                .concatWithValues("AWS")
        .log();
        fluxString.subscribe(System.out::println);
    }

    @Test
    public void testFluxOnError(){
        Flux<String> fluxString = Flux.just("Java17","Spring Boot","Microservice","Spring Reactive","Kafka")
                .concatWithValues("AWS")
                .concatWith(Flux.error(new RuntimeException("Exception occurred in Flux")))
                .log();
        fluxString.subscribe(System.out::println, (e)->System.out.println(e.getMessage()));
    }

/*
22:41:47.516 [main] INFO reactor.Flux.ConcatArray.1 -- onNext(Spring Reactive)
    Spring Reactive
22:41:47.517 [main] INFO reactor.Flux.ConcatArray.1 -- onNext(Kafka)
    Kafka
22:41:47.519 [main] INFO reactor.Flux.ConcatArray.1 -- onNext(AWS)
    AWS
22:41:47.527 [main] ERROR reactor.Flux.ConcatArray.1 -- onError(java.lang.RuntimeException: Exception occurred in Flux)
22:41:47.528 [main] ERROR reactor.Flux.ConcatArray.1 --
    java.lang.RuntimeException: Exception occurred in Flux
    at com.pkg.springreactivejavatechie.MonoFluxTest.testFluxOnError(MonoFluxTest.java:77)
    at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
    at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
    at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
    at java.base/java.lang.reflect.Method.invoke(Method.java:568)
    at org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:701)
    at org.junit.platform.commons.support.ReflectionSupport.invokeMethod(ReflectionSupport.java:502)
    at org.junit.jupiter.engine.support.MethodReflectionUtils.invoke(MethodReflectionUtils.java:45)
*/

    @Test
    public void testFluxAddAfterOnError(){
        Flux<String> fluxString = Flux.just("Java17","Spring Boot","Microservice","Spring Reactive","Kafka")
                .concatWithValues("AWS")
                .concatWith(Flux.error(new RuntimeException("Exception occurred in Flux")))
                .concatWithValues("cloud")
                .log();
        fluxString.subscribe(System.out::println, (e)->System.out.println(e.getMessage()));
        // cloud will not add after onError event
    }
}
