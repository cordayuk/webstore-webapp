package com.fakeshop.webapp.model;

import com.fakeshop.webapp.entity.Product;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ShoppingCartProduct {
    private Product product;
    private int quantity;
    private String productName;

    public ShoppingCartProduct() {
    }

    private ShoppingCartProduct(Product product, int quantity){
        this.product = product;
        this.quantity = quantity;
        this.productName = product.getName();
    }

    public static ShoppingCartProduct createShoppingCartProduct(Product product, int quantity) {
        return new ShoppingCartProduct(product, quantity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingCartProduct that = (ShoppingCartProduct) o;
        return Objects.equals(getProduct(), that.getProduct()) &&
                Objects.equals(getProductName(), that.getProductName());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getProductName());
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

}
