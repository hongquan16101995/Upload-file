package com.example.productthymeleaf.service;

import com.example.productthymeleaf.model.Product;

import java.util.List;

public interface ICRUDService {
    List<Product> findAll();

    Product findById(Long id);

    void create(Product product);

    void update(Product product);

    void delete(Long id);
}
