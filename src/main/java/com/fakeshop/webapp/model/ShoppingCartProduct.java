package com.fakeshop.webapp.model;

import com.fakeshop.webapp.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartProduct {
    private Product product;
    private int quantity;

    public ShoppingCartProduct() {
    }

    private ShoppingCartProduct(Product product, int quantity){
        this.product = product;
        this.quantity = quantity;
    }

    public static ShoppingCartProduct createShoppingCartProduct(Product product, int quantity) {
        return new ShoppingCartProduct(product, quantity);
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return product.getPrice() * quantity;
    }
}
