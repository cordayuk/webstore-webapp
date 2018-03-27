package com.fakeshop.webapp.dao;

import com.fakeshop.webapp.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao extends CrudRepository<Product, Long>{
}