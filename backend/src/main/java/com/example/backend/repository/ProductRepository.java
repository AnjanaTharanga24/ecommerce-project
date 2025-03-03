package com.example.backend.repository;

import com.example.backend.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

    Page<Product> findByCategoryId(Long id , Pageable pageable);
    Page<Product> findByNameContaining(String name , Pageable pageable);
}


