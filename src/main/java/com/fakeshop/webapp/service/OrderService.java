package com.fakeshop.webapp.service;

import com.fakeshop.webapp.entity.Order;

import java.util.Collection;
import java.util.Optional;

public interface OrderService {
    Optional<Order> findById(Long id);
    Iterable<Order> findAll();
    void save(Order order);
    void delete(Order order);
    Iterable<Order> findAllUserOrders();
    Order findSpecificUserOrder(Long id);
}
