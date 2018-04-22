package com.fakeshop.webapp.service;

import com.fakeshop.webapp.entity.Product;
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
