package com.example.webapp.service;

import com.example.webapp.model.ShoppingCart;
import com.example.webapp.entity.Product;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartImpl implements ShoppingCartService {

    @Override
    public void addProduct(ShoppingCart cart, Product product, int quantity) {
        cart.addProduct(product, quantity);
    }

    @Override
    public void removeProduct(ShoppingCart cart, Product product) {
        cart.removeProduct(product);
    }

    @Override
    public void updateProductQuantity(ShoppingCart cart, Product product, int quantity) {
        cart.updateProductQuantity(product, quantity);
    }
}
