package com.example.productthymeleaf.service;

import com.example.productthymeleaf.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements ICRUDService{

    private final List<Product> products;

    public ProductService() {
        products = new ArrayList<>();
        products.add(new Product(1L, "abc", 100D, 100, ""))
    }

    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public Product findById(Long id) {
        for (Product p : products) {
            if (p.getId().equals(id)) {
                return p;
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
        Product productUpdate = findById(product.getId());
        int index = products.indexOf(productUpdate);
        products.set(index, product);
    }

    @Override
    public void delete(Long id) {
        Product productDelete = findById(id);
        products.remove(productDelete);
    }
}
