package com.example.webapp.service;

import com.example.webapp.entity.Product;
import com.example.webapp.model.ShoppingCart;

public interface ShoppingCartService {
    void addProduct(ShoppingCart cart, Product product, int quantity);
    void removeProduct(ShoppingCart cart, Product product);
    void updateProductQuantity(ShoppingCart cart, Product product, int quantity);
}
