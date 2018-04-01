package com.fakeshop.webapp.entity;

import com.fakeshop.webapp.model.ShoppingCart;
import com.fakeshop.webapp.model.ShoppingCartProduct;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

import static com.fakeshop.webapp.entity.OrderDetails.createOrderDetails;

@Entity
@Table(name = "order_table")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String[] deliveryAddress;
    private String[] paymentAddress;
    private LocalDateTime orderDate = LocalDateTime.now();
    private double cost;

    @OneToMany(mappedBy = "order")
    private List<OrderDetails> orderDetails = new ArrayList<OrderDetails>();

    public Order() {
    }

    private Order(User user, String[] deliveryAddress, String[] paymentAddress, double cost) {
        this.user = user;
        this.deliveryAddress = deliveryAddress;
        this.paymentAddress = paymentAddress;
        this.cost = cost;
    }

    public static Order createOrder(User user, String[] deliveryAddress, String[] paymentAddress, ShoppingCart shoppingCart){
        Order order = new Order(user, deliveryAddress, paymentAddress, shoppingCart.getTotalCost());
        List<OrderDetails> details = new ArrayList<OrderDetails>();
        for(ShoppingCartProduct scp : shoppingCart.getShoppingCart()){
            details.add(createOrderDetails(order, scp.getProduct(), scp.getQuantity()));
        }
        order.setOrderDetails(details);
        return order;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String[] getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String[] deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String[] getPaymentAddress() {
        return paymentAddress;
    }

    public void setPaymentAddress(String[] paymentAddress) {
        this.paymentAddress = paymentAddress;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Collection<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }
}