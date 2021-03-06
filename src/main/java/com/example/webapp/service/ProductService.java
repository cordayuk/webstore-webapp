package com.example.webapp.service;

import com.example.webapp.entity.Product;
import org.springframework.web.multipart.MultipartFile;

public interface ProductService {
    Product findById(Long id);
    Iterable<Product> findAll();
    void save(Product product, MultipartFile file);
    void save(Product product);
    void delete(Product product);
    void deleteAll();
    Iterable<Product> findByForSaleTrue();
}
