package com.example.productthymeleaf.service;

import com.example.productthymeleaf.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductService implements ICRUDService{
    private final List<Product> products;

    public ProductService() {
        products = new ArrayList<>();
    }

    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public Product findById(Long id) {
        for (Product product : products) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        return null;
    }

    @Override
    public void create(Product product) {
        products.add(product);
    }

    @Override
    public void update(Product product) {
        Product p = findById(product.getId());
        int index = products.indexOf(p);
        products.set(index, product);
    }

    @Override
    public void delete(Long id) {
        products.remove(findById(id));
    }
}
