package com.fakeshop.webapp.service;

import com.fakeshop.webapp.model.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface ProductService {
    Product findById(Long id);
    Iterable<Product> findAll();
    void save(Product product, MultipartFile file);
    void delete(Product product);
    void deleteAll();
}
