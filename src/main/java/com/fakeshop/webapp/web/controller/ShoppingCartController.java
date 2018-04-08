package com.fakeshop.webapp.web.controller;

import com.fakeshop.webapp.model.ShoppingCart;
import com.fakeshop.webapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

public class ShoppingCartController {

    @Autowired
    OrderService orderService;

    @ModelAttribute("cart")
    public ShoppingCart getSessionShoppingCart(HttpSession session) {
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        return cart;
    }

    @RequestMapping("/cart")
    public String cart(){
        return null;
    }

    @RequestMapping(value = "/cart", method = RequestMethod.POST)
    public String cartPost(){
        return "redirect:/cart";
    }

    @RequestMapping("cart/order")
    public String order(){
        return null;
    }

    @RequestMapping(value = "cart/order", method = RequestMethod.POST)
    public String placeOrder(){
    return null;
    }

}
