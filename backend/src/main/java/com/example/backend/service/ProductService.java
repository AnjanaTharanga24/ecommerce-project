package com.example.backend.service;

import com.example.backend.controller.request.ProductRequest;
import com.example.backend.controller.response.ProductResponse;
import com.example.backend.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    ProductResponse addProduct(ProductRequest productRequest);
    List<ProductResponse> getAllProducts();
//    List<Product> findProductByCategory(Long id);
    Page<Product> findProductByCategory(Long id, Pageable pageable);
}
