package com.fakeshop.webapp.web.controller;

import com.fakeshop.webapp.entity.User;
import com.fakeshop.webapp.service.OrderService;
import com.fakeshop.webapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;


@Controller
public class AccountController {
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;

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
    public String orderDetails(@PathVariable Long orderId, Model model) {
        model.addAttribute("order", orderService.findSpecificUserOrder(orderId));
        return "shared/orderdetail";
    }

    @RequestMapping("account/details")
    public String details(Principal principal) {
        User user = userService.getCurrentUser(principal);
        return "customer/details";
    }

    @RequestMapping("account/details/edit")
    public String editDetailsForm(Model model, Principal principal){
        if(!model.containsAttribute("user")){
            model.addAttribute("user", userService.getCurrentUser(principal));
        }
        return "customer/form";
    }

    @RequestMapping(value = "/account/details/edit", method = RequestMethod.POST)
    public String editDetails(User user) {
        userService.save(user);
        return "redirect:/account";
    }

    @RequestMapping(value = "/account/details/delete", method = RequestMethod.POST)
    public String deleteUser(Principal principal, Authentication authentication){
        userService.delete(userService.getCurrentUser(principal));
        authentication.setAuthenticated(false);
        return "redirect:/";
    }
}
