package com.fakeshop.webapp.service;

import com.fakeshop.webapp.model.Product;

import java.util.Optional;

public interface ProductService {
    Optional<Product> findById(Long id);
    Iterable<Product> findAll();
    void save(Product product);
    void delete(Product product);
    void deleteAll();
}
