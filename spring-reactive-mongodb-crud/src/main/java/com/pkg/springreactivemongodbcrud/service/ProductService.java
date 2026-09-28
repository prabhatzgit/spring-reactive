package com.pkg.springreactivemongodbcrud.service;


import com.pkg.springreactivemongodbcrud.dto.ProductDTO;
import com.pkg.springreactivemongodbcrud.repository.ProductRepository;
import com.pkg.springreactivemongodbcrud.utils.AppUtils;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository repository){
        this.productRepository = repository;
    }

    public Flux<ProductDTO> getProducts(){
        return productRepository.findAll().map(AppUtils::entityToDto);
    }

    public Mono<ProductDTO> getProduct(String id){
        return productRepository.findById(id).map(AppUtils::entityToDto);
    }

    public Flux<ProductDTO> getProductInRange(double min, double max){
        return productRepository.findByPriceBetween(Range.closed(min,max));
    }

    public Mono<ProductDTO> saveProduct(Mono<ProductDTO> productDtoMono){
        System.out.println("service method called ...");
      return  productDtoMono.map(AppUtils::dtoToEntity)
                .flatMap(productRepository::insert)
                .map(AppUtils::entityToDto);
    }

    public Mono<ProductDTO> updateProduct(Mono<ProductDTO> productDtoMono, String id){
       return productRepository.findById(id)
                .flatMap(p->productDtoMono.map(AppUtils::dtoToEntity)
                .doOnNext(e->e.setId(id)))
                .flatMap(productRepository::save)
                .map(AppUtils::entityToDto);

    }

    public Mono<Void> deleteProduct(String id){
        return productRepository.deleteById(id);
    }
}