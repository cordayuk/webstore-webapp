package com.example.webapp.service;

import com.example.webapp.entity.OrderDetails;

import java.util.Optional;

public interface OrderDetailsService {
    Optional<OrderDetails> findById(Long id);
    Iterable<OrderDetails> findAll();
    void save(OrderDetails orderDetails);
    void delete(OrderDetails orderDetails);
}
