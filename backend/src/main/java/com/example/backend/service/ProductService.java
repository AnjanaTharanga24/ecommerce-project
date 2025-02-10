package com.example.backend.service;

import com.example.backend.controller.request.ProductRequest;
import com.example.backend.controller.response.ProductResponse;
import com.example.backend.entity.Product;
import com.example.backend.entity.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    ProductResponse addProduct(ProductRequest productRequest);
    List<ProductResponse> getAllProducts();
    Product getProductById(Long id);
    Page<Product> findProductByCategory(Long id, Pageable pageable);
    List<ProductCategory> getAllProductCategories();
    Page<Product> productsSearchByName(String name , Pageable pageable);
}
