package com.example.webapp.service;

import com.example.webapp.dao.OrderDetailsDao;
import com.example.webapp.entity.OrderDetails;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class OrderDetailsServiceImpl implements OrderDetailsService {
    @Autowired
    private OrderDetailsDao orderDetailsDao;
    @Override
    public Optional<OrderDetails> findById(Long id) {
        return orderDetailsDao.findById(id);
    }

    @Override
    public Iterable<OrderDetails> findAll() {
        return orderDetailsDao.findAll();
    }

    @Override
    public void save(OrderDetails orderDetails) {
        orderDetailsDao.save(orderDetails);
    }

    @Override
    public void delete(OrderDetails orderDetails) {
        orderDetailsDao.delete(orderDetails);
    }
}
