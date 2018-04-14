package com.fakeshop.webapp.config.interceptors;

import com.fakeshop.webapp.model.ShoppingCart;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShoppingCartInterceptor extends HandlerInterceptorAdapter {

    // Adds shopping cart object to every user session no matter which part of site they have entered on.
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        if(session.getAttribute("cart") == null) {
            session.setAttribute("cart", new ShoppingCart());
        }

        return true;
    }
}
