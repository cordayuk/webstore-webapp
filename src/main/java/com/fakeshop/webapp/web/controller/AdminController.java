package com.fakeshop.webapp.web.controller;

import com.fakeshop.webapp.dao.OrderDao;
import com.fakeshop.webapp.entity.Product;
import com.fakeshop.webapp.service.OrderService;
import com.fakeshop.webapp.service.ProductService;
import com.fakeshop.webapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class AdminController {
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;

    // Admin Homepage
    @RequestMapping("/admin")
    public String adminHome(Model model){


        return "admin/home";
    }

    //Product Homepage
    @RequestMapping("/admin/products")
    public String productOverview(Model model) {
        Iterable<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "admin/allproducts";
    }

    // View more details on specific product, give access to edit or delete
    @RequestMapping("/admin/products/{productId}")
    public String productDetails(@PathVariable Long productId, Model model){
        Product product = productService.findById(productId);
        model.addAttribute("product", product);
        return "shared/product";
    }

    // add product form
    @RequestMapping("/admin/products/add")
    public String addProductForm(Model model) {
        if(!model.containsAttribute("product")){
            model.addAttribute("product", new Product());
        }
        // action must post to /admin/products/add
        return "admin/form";
    }

    //receive add product form
    @RequestMapping(value = "/admin/products/add", method = RequestMethod.POST)
    public String addProduct(Product product, @PathVariable MultipartFile file){
        productService.save(product, file);

        return String.format("redirect:/products/%s", product.getId());
    }

    // Form for editing products
    @RequestMapping("/admin/products/{productId}/edit")
    public String editProduct(@PathVariable Long productId, Model model) {
        if(!model.containsAttribute("product")){
            model.addAttribute("product", productService.findById(productId));
        }
        //action to post to /admin/products/{productId}/edit
        return "admin/form";
    }
    //receive edit product form
    @RequestMapping(value = "/admin/products/{productId}/edit", method = RequestMethod.POST)
    public String updateProduct(@PathVariable Long productId, Product product, MultipartFile file) {
        productService.save(product, file);

        return String.format("redirect:/admin/products/%s", product.getId());
    }

    //delete a product
    @RequestMapping(value = "/admin/products/{productId}/delete", method = RequestMethod.POST)
    public String deleteAllProducts(@PathVariable Long productId){
        Product product = productService.findById(productId);
        productService.delete(product);

        return "redirect:/admin/allproducts";
    }

    //view all orders
    @RequestMapping("/admin/orders")
    public String adminOrders(Model model) {
        model.addAttribute("orders", orderService.findAll());
        return "admin/orders";
    }

    //View order details
    @RequestMapping("/admin/order/{orderId}")
    public String orderDetail(@PathVariable Long orderId, Model model) {
        model.addAttribute("order", orderService.findById(orderId));

        return "shared/orderdetail";
    }
}
