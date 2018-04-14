package com.fakeshop.webapp.web.controller;

import com.fakeshop.webapp.entity.Product;
import com.fakeshop.webapp.entity.User;
import com.fakeshop.webapp.model.ShoppingCart;
import com.fakeshop.webapp.service.ProductService;
import com.fakeshop.webapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class HomePageController {
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;

    @ModelAttribute("cart")
    public ShoppingCart getSessionShoppingCart(HttpSession session) {
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        return cart;
    }

    @RequestMapping("/")
    public String homepage(Model model, HttpSession httpSession){
        Iterable<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "home";
    }

    @RequestMapping("/signup")
    public String signUp(Model model) {
        model.addAttribute("newUser", new User());
        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signUpPost(@ModelAttribute User user) {
        userService.saveNewCustomer(user);
        return "redirect:/login";
    }

    @RequestMapping("/login")
    public String userLogin(){
        return "login";
    }

    @RequestMapping("/{productId}")
    public String productPage(@PathVariable Long productId, Model model) {
        Product product = productService.findById(productId);
        model.addAttribute("product", product);

        return "shared/product";
    }

    // return a product image
    @RequestMapping("/products/{productId}.jpg")
    @ResponseBody
    public byte[] productImage(@PathVariable Long productId) {
        return productService.findById(productId).getPicture();
    }

}
