package com.fakeshop.webapp.service;

import com.fakeshop.webapp.dao.ProductDao;
import com.fakeshop.webapp.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

    public Product findById(Long id) {
            return productDao.findById(id).get();
    }

    @Override
    public Iterable<Product> findAll() {
        return productDao.findAll();
    }

    @Override
    public void save(Product product, MultipartFile file) {
        try {
            product.setPicture(file.getBytes());
            productDao.save(product);
        } catch (IOException ioe){
            System.out.println("Unable to get byte array form uploaded file");
        }
    }

    @Override
    public void delete(Product product) {
        productDao.delete(product);
    }

    @Override
    public void deleteAll() {
        productDao.deleteAll();
    }


}
