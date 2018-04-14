package com.fakeshop.webapp.service;

import com.fakeshop.webapp.dao.OrderDao;
import com.fakeshop.webapp.dao.OrderDetailsDao;
import com.fakeshop.webapp.entity.Order;
import com.fakeshop.webapp.entity.OrderDetails;
import com.fakeshop.webapp.entity.User;
import com.fakeshop.webapp.model.ShoppingCart;
import com.fakeshop.webapp.model.ShoppingCartProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.fakeshop.webapp.entity.OrderDetails.createOrderDetails;

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

                Order order = new Order();
                order = Order.createOrder(user, deliveryAddress, paymentAddress, cart);
                List<OrderDetails> details = new ArrayList<OrderDetails>();
                for(ShoppingCartProduct scp : cart){
                    OrderDetails orderDetails = createOrderDetails(order, scp.getProduct(), scp.getQuantity());
                    details.add(orderDetails);
                }
                order.setOrderDetails(details);
                save(order);
                return true;

        }
        return false;
    }
}
