package com.fakeshop.webapp.dao;

import com.fakeshop.webapp.entity.OrderDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailsDao extends CrudRepository<OrderDetails, Long> {
}
