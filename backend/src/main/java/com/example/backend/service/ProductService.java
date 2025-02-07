package com.example.backend.service;

import com.example.backend.controller.request.ProductRequest;
import com.example.backend.controller.response.ProductResponse;

import java.util.List;

public interface ProductService {

    ProductResponse addProduct(ProductRequest productRequest);
    List<ProductResponse> getAllProducts();
}
