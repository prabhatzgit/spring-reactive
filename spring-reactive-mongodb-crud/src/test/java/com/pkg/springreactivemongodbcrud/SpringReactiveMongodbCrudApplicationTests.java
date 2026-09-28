package com.pkg.springreactivemongodbcrud;

import com.pkg.springreactivemongodbcrud.controller.ProductController;
import com.pkg.springreactivemongodbcrud.dto.ProductDTO;
import com.pkg.springreactivemongodbcrud.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.webflux.test.autoconfigure.WebFluxTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@WebFluxTest(ProductController.class)
class SpringReactiveMongoCrudApplicationTests {

    @Autowired
    private WebTestClient webTestClient;

    @MockitoBean
    private ProductService service;

    @Test
    void addProductTest() {
        Mono<ProductDTO> productDTOMono = Mono.just(new ProductDTO("102", "mobile", 1, 10000));
        when(service.saveProduct(any())).thenReturn(productDTOMono);

        webTestClient.post().uri("/products")
                .body(productDTOMono, ProductDTO.class)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void getProductsTest() {
        Flux<ProductDTO> productDTOFlux = Flux.just(
                new ProductDTO("102", "mobile", 1, 10000),
                new ProductDTO("103", "TV", 1, 50000)
        );
        when(service.getProducts()).thenReturn(productDTOFlux);

        Flux<ProductDTO> responseBody = webTestClient.get().uri("/products")
                .exchange()
                .expectStatus().isOk()
                .returnResult(ProductDTO.class)
                .getResponseBody();

        StepVerifier.create(responseBody)
                .expectSubscription()
                .expectNext(new ProductDTO("102", "mobile", 1, 10000))
                .expectNext(new ProductDTO("103", "TV", 1, 50000))
                .verifyComplete();
    }

    @Test
    void getProductTest() {
        Mono<ProductDTO> productDTOMono = Mono.just(new ProductDTO("102", "mobile", 1, 10000));
        when(service.getProduct(any())).thenReturn(productDTOMono);

        Flux<ProductDTO> responseBody = webTestClient.get().uri("/products/102")
                .exchange()
                .expectStatus().isOk()
                .returnResult(ProductDTO.class)
                .getResponseBody();

        StepVerifier.create(responseBody)
                .expectSubscription()
                .expectNextMatches(p -> p.getName().equals("mobile"))
                .verifyComplete();
    }

    @Test
    void updateProductTest() {
        Mono<ProductDTO> productDTOMono = Mono.just(new ProductDTO("102", "mobile", 1, 10000));
        when(service.updateProduct(any(), any())).thenReturn(productDTOMono);

        webTestClient.put().uri("/products/update/102")
                .body(productDTOMono, ProductDTO.class)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void deleteProductTest() {
        given(service.deleteProduct(any())).willReturn(Mono.empty());

        webTestClient.delete().uri("/products/delete/102")
                .exchange()
                .expectStatus().isOk();
    }
}
