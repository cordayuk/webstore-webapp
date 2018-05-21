package com.example.webapp.dao;

import com.example.webapp.entity.OrderDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailsDao extends CrudRepository<OrderDetails, Long> {
}
