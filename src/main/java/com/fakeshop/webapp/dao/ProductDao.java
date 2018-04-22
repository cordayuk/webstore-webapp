package com.fakeshop.webapp.dao;

import com.fakeshop.webapp.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao extends CrudRepository<Product, Long>{
    public Iterable<Product> findByForSaleTrue();
}
