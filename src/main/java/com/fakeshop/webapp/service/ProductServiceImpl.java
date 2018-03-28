package com.fakeshop.webapp.service;

import com.fakeshop.webapp.dao.ProductDao;
import com.fakeshop.webapp.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

    public Optional<Product> findById(Long id) {
        return productDao.findById(id);
    }

    @Override
    public Iterable<Product> findAll() {
        return productDao.findAll();
    }

    @Override
    public void save(Product product) {
        productDao.save(product);
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
