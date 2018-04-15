package com.fakeshop.webapp.web.controller;

import com.fakeshop.webapp.model.ShoppingCart;
import com.fakeshop.webapp.service.OrderService;
import com.fakeshop.webapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;

    @RequestMapping("/checkout")
    public String checkout(Principal principal, Model model, HttpSession session){
        if(principal == null){
            return "login";
        }
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if(cart.isEmpty()){
            return "redirect:/";
        }

        model.addAttribute("user", userService.getCurrentUser(principal));
        model.addAttribute("cart", cart);

        return "checkout";
    }

    @RequestMapping(value = "/checkout", method = RequestMethod.POST)
    public String checkoutPost(HttpSession session, Principal principal, @RequestParam String deliveryAddress, @RequestParam String paymentAddress) {

        orderService.createOrder((ShoppingCart) session.getAttribute("cart"), userService.getCurrentUser(principal), deliveryAddress, paymentAddress);
        session.removeAttribute("cart");
        return "redirect:/account/orders";
    }
}
