package com.example.productthymeleaf.model;

import org.springframework.web.multipart.MultipartFile;


public class Product {

    private Long id;
    private String name;
    private Double price;
    private Integer quantity;
    private String image;

    private MultipartFile file;

    public Product() {
    }

    public Product(Long id, String name, Double price, Integer quantity, String image, MultipartFile file) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
        this.file = file;
    }

    public Product(Long id, String name, Double price, Integer quantity, String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
