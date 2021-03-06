package com.example.webapp.web.controller;

import com.example.webapp.service.RoleService;
import com.example.webapp.entity.Product;
import com.example.webapp.entity.User;
import com.example.webapp.service.OrderService;
import com.example.webapp.service.ProductService;
import com.example.webapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@Controller
public class AdminController {
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private RoleService roleService;

    // Admin Homepage
    @RequestMapping("/admin")
    public String adminHome(){
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
        model.addAttribute("admin", true);
        model.addAttribute("product", product);
        return "shared/product";
    }

    // add product form
    @RequestMapping("/admin/products/add")
    public String addProductForm(Model model) {
        if(!model.containsAttribute("product")){
            model.addAttribute("product", new Product());
        }
        model.addAttribute("action", "/admin/products/add");
        model.addAttribute("heading", "Add");
        // action must post to /admin/products/add
        return "admin/productform";
    }

    //receive add product form
    @RequestMapping(value = "/admin/products/add", method = RequestMethod.POST)
    public String addProduct(Product product, @RequestParam MultipartFile file){
        productService.save(product, file);

        return String.format("redirect:/admin/products/%s", product.getId());
    }

    // Form for editing products
    @RequestMapping("/admin/products/{productId}/edit")
    public String editProduct(@PathVariable Long productId, Model model) {
        if(!model.containsAttribute("product")){
            model.addAttribute("product", productService.findById(productId));
        }
        model.addAttribute("heading", "Edit");
        //action to post to /admin/products/{productId}/edit
        return "admin/productform";
    }
    //receive edit product form - might not need at all. works if form is passed to product add
    @RequestMapping(value = "/admin/products/{productId}/edit", method = RequestMethod.POST)
    public String updateProduct(@PathVariable Long productId, Product product, @RequestParam MultipartFile file) {
        productService.save(product, file);

        return String.format("redirect:/admin/products/%s", product.getId());
    }

    //delete a product
    @RequestMapping(value = "/admin/products/{productId}/delete", method = RequestMethod.POST)
    public String removeProductForSale(@PathVariable Long productId, Principal principal){
        User user = userService.getCurrentUser(principal);
        if(user.getRole().getName().equals("ROLE_ADMIN")) {
            Product product = productService.findById(productId);
            product.setForSale(false);
            productService.save(product);
        }

        return "redirect:/admin/products";
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
        model.addAttribute("order", orderService.findById(orderId).get());

        return "shared/orderdetail";
    }

    // view all users
    @RequestMapping("/admin/users")
    public String users(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin/users";
    }

    //View User details
    @RequestMapping("/admin/users/{userId}")
    public String userDetails(@PathVariable Long userId, Model model) {
        User user = userService.findById(userId);
        if(user != null){
            model.addAttribute("user", user);
            model.addAttribute("orders", orderService.findAllOrdersByUserId(user.getId()));
            return "admin/userdetails";
        }
        else
            return "redirect:/admin/users";
    }

    //edit user details
    @RequestMapping("/admin/users/{userId}/edit")
    public String editUser(@PathVariable Long userId, Model model) {
        User user = userService.findById(userId);
        if(user != null){
            model.addAttribute("user", user);
            return "admin/userform";
        }
        else
            return "redirect:/admin/users";
    }

    //Receive edit details
    @RequestMapping(value = "/admin/users/edit", method = RequestMethod.POST)
    public String editUserPost(User user, @RequestParam Long roleId, @RequestParam boolean nonExpired, @RequestParam boolean nonLocked, @RequestParam boolean enabled) {

        user.setRole(roleService.findById(roleId));
        user.setNonExpired(nonExpired);
        user.setNonLocked(nonLocked);
        user.setEnabled(enabled);

        userService.save(user);

        return String.format("redirect:/admin/users/%s", user.getId());
    }
}
