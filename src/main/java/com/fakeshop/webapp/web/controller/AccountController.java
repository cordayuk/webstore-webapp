package com.fakeshop.webapp.web.controller;

import com.fakeshop.webapp.model.User;
import com.fakeshop.webapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;


@Controller
public class AccountController {
    @Autowired
    private UserService userService;

    @RequestMapping("/account")
    public String account(Principal principal){
        User user = userService.getCurrentUser(principal);
        return null;
    }

    @RequestMapping("/account/orders")
    public String orders(Principal principal) {
        User user = userService.getCurrentUser(principal);
        return null;
    }

    @RequestMapping("account/details")
    public String details(Principal principal) {
        User user = userService.getCurrentUser(principal);
        return null;
    }

    @RequestMapping("account/details/edit")
    public String editDetailsForm(Principal principal){
        User user = userService.getCurrentUser(principal);
        return null;
    }

    @RequestMapping(value = "/account/details/edit", method = RequestMethod.POST)
    public String editDetails() {
        return null;
    }

    @RequestMapping(value = "/account/details/delete", method = RequestMethod.POST)
    public String deleteUser(Principal principal, Authentication authentication){
        userService.delete(userService.getCurrentUser(principal));
        authentication.setAuthenticated(false);
        return "redirect:/";
    }
}
