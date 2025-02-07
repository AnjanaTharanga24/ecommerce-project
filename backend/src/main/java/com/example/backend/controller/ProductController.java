package com.example.backend.controller;

import com.example.backend.controller.request.ProductRequest;
import com.example.backend.controller.response.ProductResponse;
import com.example.backend.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;


    @PostMapping
    public ProductResponse addItems(@RequestBody ProductRequest productRequest){
       return productService.addProduct(productRequest);
    }

    @GetMapping
    public List<ProductResponse> getAllItems(){
      return productService.getAllProducts();
    }
}
