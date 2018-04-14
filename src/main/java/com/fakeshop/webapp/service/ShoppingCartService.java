package com.fakeshop.webapp.service;

import com.fakeshop.webapp.entity.Product;
import com.fakeshop.webapp.model.ShoppingCart;

public interface ShoppingCartService {
    void addProduct(ShoppingCart cart, Product product, int quantity);
    void removeProduct(ShoppingCart cart, Product product);
    void updateProductQuantity(ShoppingCart cart, Product product, int quantity);
}
