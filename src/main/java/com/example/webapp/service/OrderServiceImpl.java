package com.example.webapp.service;

import com.example.webapp.dao.OrderDetailsDao;
import com.example.webapp.entity.Order;
import com.example.webapp.entity.OrderDetails;
import com.example.webapp.entity.User;
import com.example.webapp.model.ShoppingCart;
import com.example.webapp.model.ShoppingCartProduct;
import com.example.webapp.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private OrderDetailsDao orderDetailsDao;

    @Override
    public Optional<Order> findById(Long id) {
        return orderDao.findById(id);
    }

    @Override
    public Iterable<Order> findAll() {
        return orderDao.findAll();
    }

    @Override
    public void save(Order order) {
        orderDao.save(order);
    }

    @Override
    public void delete(Order order) {
        orderDao.delete(order);
    }

    @Override
    public Iterable<Order> findAllUserOrders() {
        return orderDao.findAllUserOrders();
    }

    @Override
    public Order findSpecificUserOrder(Long id) {
        return orderDao.findSpecificUserOrder(id);
    }

    @Override
    public boolean createOrder(ShoppingCart cart, User user, String deliveryAddress, String paymentAddress) {
        if(!cart.isEmpty()) {
                Order order = Order.createOrder(user, deliveryAddress, paymentAddress, cart);
                List<OrderDetails> details = new ArrayList<>();
                for(ShoppingCartProduct scp : cart){
                    OrderDetails orderDetails = OrderDetails.createOrderDetails(order, scp.getProduct(), scp.getQuantity());
                    details.add(orderDetails);
                }
                order.setOrderDetails(details);
                save(order);
                return true;

        }
        return false;
    }

    @Override
    public Iterable<Order> findAllOrdersByUserId(Long id) {
        return orderDao.findOrdersByUserId(id);
    }
}
