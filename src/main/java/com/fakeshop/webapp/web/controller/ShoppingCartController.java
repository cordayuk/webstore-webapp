package com.fakeshop.webapp.web.controller;

import com.fakeshop.webapp.entity.Product;
import com.fakeshop.webapp.model.ShoppingCart;
import com.fakeshop.webapp.service.OrderService;
import com.fakeshop.webapp.service.ProductService;
import com.fakeshop.webapp.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.fakeshop.webapp.service.Utils.getPreviousPage;

@Controller
public class ShoppingCartController {

    @Autowired
    OrderService orderService;
    @Autowired
    ShoppingCartService shoppingCartService;
    @Autowired
    ProductService productService;

    @ModelAttribute("cart")
    public ShoppingCart getSessionShoppingCart(HttpSession session) {
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        return cart;
    }

    @RequestMapping("/cart")
    public String cart(){

        return "/customer/cart";
    }

    @RequestMapping(value = "/cartadd", method = RequestMethod.POST)
    public String cartadd(HttpSession session, HttpServletRequest request, @RequestParam Long id, int quantity){
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        shoppingCartService.addProduct(cart, productService.findById(id), quantity);

        return "redirect:" + getPreviousPage(request);
    }

    @RequestMapping(value = "/cartremove", method = RequestMethod.POST)
    public String cartRemove(HttpSession session, HttpServletRequest request, @RequestParam Long id){
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        shoppingCartService.removeProduct(cart, productService.findById(id));

        return "redirect:" + getPreviousPage(request);
    }

    @RequestMapping(value = "/cartupdate", method = RequestMethod.POST)
    public String updateCartProduct(HttpSession session, HttpServletRequest request, @RequestParam Long id, @RequestParam int quantity){
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        shoppingCartService.updateProductQuantity(cart, productService.findById(id), quantity);

        return "redirect:" + getPreviousPage(request);
    }

}
