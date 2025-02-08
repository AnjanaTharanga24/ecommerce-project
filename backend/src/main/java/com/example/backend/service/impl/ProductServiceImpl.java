package com.example.backend.service.impl;

import com.example.backend.controller.request.ProductRequest;
import com.example.backend.controller.response.ProductResponse;
import com.example.backend.entity.Product;
import com.example.backend.entity.ProductCategory;
import com.example.backend.repository.ProductCategoryRepository;
import com.example.backend.repository.ProductRepository;
import com.example.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {


    private final ProductRepository productRepository;

    private final ProductCategoryRepository productCategoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, ProductCategoryRepository productCategoryRepository) {
        this.productRepository = productRepository;
        this.productCategoryRepository = productCategoryRepository;
    }

    @Override
    public ProductResponse addProduct(ProductRequest productRequest) {

        Product product = new Product();

        product.setSku(productRequest.getSku());
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setUnitPrice(productRequest.getUnitPrice());
        product.setImageUrl(productRequest.getImageUrl());
        product.setActive(productRequest.isActive());
        product.setUnitsInStock(productRequest.getUnitsInStock());

        ProductCategory category = productCategoryRepository.findById(productRequest.getCategory())
                .orElseThrow(() -> new RuntimeException("category not found"));

        product.setCategory(category);

        Product savedProduct = productRepository.save(product);

        ProductResponse response = new ProductResponse();
        response.setCategory(savedProduct.getCategory().getCategoryName());
        response.setSku(savedProduct.getSku());
        response.setName(savedProduct.getName());
        response.setDescription(savedProduct.getDescription());
        response.setUnitPrice(savedProduct.getUnitPrice().floatValue());
        response.setImageUrl(savedProduct.getImageUrl());
        response.setActive(savedProduct.isActive());
        response.setUnitsInStock(savedProduct.getUnitsInStock());
        response.setDateCreated(savedProduct.getDateCreated());
        response.setLastUpdated(savedProduct.getLastUpdated());

        return response;
    }

    @Override
    public List<ProductResponse> getAllProducts() {

        List<Product> products = productRepository.findAll();
        List<ProductResponse> responses = new ArrayList<>();

        for (Product product : products) {
            ProductResponse response = new ProductResponse();
            response.setCategory(product.getCategory().getCategoryName());
            response.setSku(product.getSku());
            response.setName(product.getName());
            response.setDescription(product.getDescription());
            response.setUnitPrice(product.getUnitPrice().floatValue());
            response.setImageUrl(product.getImageUrl());
            response.setActive(product.isActive());
            response.setUnitsInStock(product.getUnitsInStock());
            response.setDateCreated(product.getDateCreated());
            response.setLastUpdated(product.getLastUpdated());

            responses.add(response);
        }

        return responses;
    }

    @Override
    public Page<Product> findProductByCategory(Long id, Pageable pageable) {
        if (id == null){
            throw new IllegalArgumentException("Category ID cannot be null");
        }
        return productRepository.findByCategoryId(id, pageable);
    }
}
