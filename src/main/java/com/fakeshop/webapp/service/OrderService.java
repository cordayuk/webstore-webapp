package com.fakeshop.webapp.service;

import com.fakeshop.webapp.entity.Order;
import com.fakeshop.webapp.entity.User;
import com.fakeshop.webapp.model.ShoppingCart;

import java.util.Collection;
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
