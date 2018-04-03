package com.fakeshop.webapp.model;

import com.fakeshop.webapp.entity.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.fakeshop.webapp.model.ShoppingCartProduct.createShoppingCartProduct;

@Component
public class ShoppingCart {

    private List<ShoppingCartProduct> shoppingCart = new ArrayList<>();

    public ShoppingCart() {
    }

    public List<ShoppingCartProduct> getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(List<ShoppingCartProduct> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public void addProduct(Product product, int quantity){
        boolean inCart = false;
        for(ShoppingCartProduct scp : shoppingCart){
            if(scp.getProduct() == product){
                inCart = true;
                scp.setQuantity(scp.getQuantity() + quantity);
                break;
            }
        }
        if(!inCart){
            shoppingCart.add(createShoppingCartProduct(product, quantity));
        }
    }

    public void removeProduct(Product product, int quantity) throws Exception {
        boolean inCart = false;
        for(ShoppingCartProduct scp : shoppingCart){
            if(scp.getProduct() == product){
                inCart = true;
                if(scp.getQuantity() - quantity <= 0){
                    shoppingCart.remove(scp);
                    break;
                }else {
                    scp.setQuantity(scp.getQuantity() - quantity);
                    break;
                }
            }
        }
        if(!inCart){
            throw new Exception("Product not found");
        }
    }

    public double getTotalCost(){
        double totalCost = 0.0;
        for(ShoppingCartProduct scp: shoppingCart){
            totalCost += scp.getTotalPrice();
        }
        return totalCost;
    }
}
