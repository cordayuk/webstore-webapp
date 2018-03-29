package com.fakeshop.webapp.web.controller;

import com.fakeshop.webapp.entity.Product;
import com.fakeshop.webapp.service.ProductService;
import com.fakeshop.webapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class AdminController {
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;

    // Admin Homepage
    @RequestMapping("/admin")
    public String adminHome(Model model){
        return "secured";
    }
    //Product Homepage
    @RequestMapping("/admin/products")
    public String productOverview(Model model) {
        Iterable<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return null;
    }

    // View more details on specific product, give access to edit or delete
    @RequestMapping("/admin/products/{productId}")
    public String productDetails(@PathVariable Long productId, Model model){
        Product product = productService.findById(productId);
        model.addAttribute("product", product);
        return null;
    }

    // add product form
    @RequestMapping("/admin/products/add")
    public String addProductForm(Model model) {
        if(!model.containsAttribute("product")){
            model.addAttribute("product", new Product());
        }
        // action must post to /admin/products/add
        return null;
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
        return null;
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

        return "redirect:/admin/products";
    }







}
