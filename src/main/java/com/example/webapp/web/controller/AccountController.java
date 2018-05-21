package com.example.webapp.web.controller;

import com.example.webapp.entity.Order;
import com.example.webapp.model.ShoppingCart;
import com.example.webapp.entity.User;
import com.example.webapp.service.OrderService;
import com.example.webapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Optional;


@Controller
public class AccountController {
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;

    @ModelAttribute("cart")
    public ShoppingCart getSessionShoppingCart(HttpSession session) {
        return (ShoppingCart) session.getAttribute("cart");
    }

    @RequestMapping("/account")
    public String account(Model model){
        return "customer/account";
    }

    @RequestMapping("/account/orders")
    public String orders(Model model) {
        model.addAttribute("orders", orderService.findAllUserOrders());
        return "customer/orders";
    }

    @RequestMapping("/account/orders/{orderId}")
    public String orderDetails(@PathVariable Long orderId, Model model, Principal principal) {
        User currentUser = userService.getCurrentUser(principal);
        Optional<Order> order = orderService.findById(orderId);
        if(order.isPresent()) {
            if(order.get().getUser().equals(currentUser)) {
                model.addAttribute("order", orderService.findSpecificUserOrder(orderId));
                return "shared/orderdetail";
            }
            else
                return "redirect:/account/orders";
        }
        else
            return "redirect:/account/orders";
    }

    @RequestMapping("account/details")
    public String details(Principal principal, Model model) {
        User user = userService.getCurrentUser(principal);
        model.addAttribute("user", user);
        return "customer/details";
    }

    @RequestMapping(value = "/account/details/edit", method = RequestMethod.POST)
    public String editDetails(@RequestParam Long id, @RequestParam String name, Principal principal) {
        User user = userService.getCurrentUser(principal);
        if(user.getId() == id){
            user.setName(name);
            userService.save(user);
        }
        return "redirect:/account";
    }

    @RequestMapping(value = "/account/details/delete", method = RequestMethod.POST)
    public String deleteUser(Principal principal, Authentication authentication){
        User user = userService.getCurrentUser(principal);
        user.setEnabled(false);
        userService.save(user);
        authentication.setAuthenticated(false);
        return "redirect:/";
    }
}
