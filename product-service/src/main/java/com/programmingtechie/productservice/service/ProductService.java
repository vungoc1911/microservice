package com.programmingtechie.productservice.service;

import com.programmingtechie.productservice.Repository.ProductRepository;
import com.programmingtechie.productservice.dto.ProductRequest;
import com.programmingtechie.productservice.dto.ProductResponse;
import com.programmingtechie.productservice.model.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    public void createProduct(ProductRequest request) {
        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .build();
        productRepository.save(product);
        log.info("product is saved" + product.getId());
    }

    public List<ProductResponse> getAllProduct() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::mapToProductResponse).collect(Collectors.toList());
    }

    private ProductResponse mapToProductResponse(Product product) {
        return  ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
