package com.fakeshop.webapp.web.controller;

import com.fakeshop.webapp.entity.Product;
import com.fakeshop.webapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomePageController {
    @Autowired
    private ProductService productService;

    @RequestMapping("/")
    public String homepage(Model model){
        Iterable<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "home";
    }

    @RequestMapping("/login")
    public String userLogin(Model model){

        return "login";
    }

    @RequestMapping("/{productId}")
    public String productPage(@RequestParam Long productId, Model model) {
        Product product = productService.findById(productId);
        model.addAttribute("product", product);

        return null;
    }

    @RequestMapping("/checkout")
    public String checkout() {
        return null;
    }

}
