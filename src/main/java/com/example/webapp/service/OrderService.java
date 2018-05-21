package com.example.webapp.service;

import com.example.webapp.entity.Order;
import com.example.webapp.entity.User;
import com.example.webapp.model.ShoppingCart;

import java.util.Optional;

public interface OrderService {
    Optional<Order> findById(Long id);
    Iterable<Order> findAll();
    void save(Order order);
    void delete(Order order);
    Iterable<Order> findAllUserOrders();
    Order findSpecificUserOrder(Long id);
    boolean createOrder(ShoppingCart cart, User user, String deliveryAddress, String paymentAddress);
    Iterable<Order> findAllOrdersByUserId(Long id);
}
