package com.example.webapp.dao;

import com.example.webapp.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao extends CrudRepository<Product, Long>{
    public Iterable<Product> findByForSaleTrue();
}
