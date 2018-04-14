package com.fakeshop.webapp.model;

import com.fakeshop.webapp.entity.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@Component
public class ShoppingCart implements Collection<ShoppingCartProduct> {

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
            if(scp.getProduct().equals(product)){
                inCart = true;
                scp.setQuantity(scp.getQuantity() + quantity);
            }
        }
        if(!inCart){
            shoppingCart.add(ShoppingCartProduct.createShoppingCartProduct(product, quantity));
        }

    }

    public void removeProduct(Product product) {
        for(Iterator<ShoppingCartProduct> iterator = shoppingCart.iterator(); iterator.hasNext();){
            ShoppingCartProduct scp = iterator.next();
            if(scp.getProduct().equals(product)){
                iterator.remove();
            }
        }
    }

    public void updateProductQuantity(Product product, int quantity) {
        for(Iterator<ShoppingCartProduct> iterator = shoppingCart.iterator(); iterator.hasNext();){
            ShoppingCartProduct scp = iterator.next();
            if(scp.getProduct().equals(product)){
                if(quantity <= 0){
                    iterator.remove();
                }
                else{
                    scp.setQuantity(quantity);
                }
            }
        }
    }

    public double getTotalCost(){
        double totalCost = 0.0;
        for(ShoppingCartProduct scp: shoppingCart){
            totalCost += scp.getTotalPrice();
        }
        return totalCost;
    }

    @Override
    public int size() {
        return shoppingCart.size();
    }

    @Override
    public boolean isEmpty() {
        return shoppingCart.isEmpty();
    }

    public boolean contains(Product product){
        for(ShoppingCartProduct scp : shoppingCart){
            if(scp.getProduct() == product){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(Object o) {
        if(o.getClass() == ShoppingCartProduct.class){
            return shoppingCart.contains(o);
        }
        return false;
    }

    @Override
    public Iterator<ShoppingCartProduct> iterator() {
        return shoppingCart.iterator();
    }

    @Override
    public Object[] toArray() {
        return shoppingCart.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return shoppingCart.toArray(a);
    }

    @Override
    public boolean add(ShoppingCartProduct shoppingCartProduct) {
        return shoppingCart.add(shoppingCartProduct);
    }

    @Override
    public boolean remove(Object o) {
        if(o.getClass() == ShoppingCartProduct.class){
            return shoppingCart.remove(o);
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return shoppingCart.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends ShoppingCartProduct> c) {
        return shoppingCart.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return shoppingCart.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return shoppingCart.retainAll(c);
    }

    @Override
    public void clear() {
        shoppingCart.clear();
    }
}
