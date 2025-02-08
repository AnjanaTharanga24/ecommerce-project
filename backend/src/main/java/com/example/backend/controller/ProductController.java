package com.example.backend.controller;

import com.example.backend.controller.request.ProductRequest;
import com.example.backend.controller.response.ProductResponse;
import com.example.backend.entity.Product;
import com.example.backend.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
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

    @GetMapping("/search/{categoryId}")
    public Page<Product> findProductByCategoryId(
            @PathVariable("categoryId") Long id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        Sort.Direction dir = direction.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(dir, sortBy));
        return productService.findProductByCategory(id, pageable);
    }
}
