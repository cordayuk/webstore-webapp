package com.example.webapp.service;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class Utils {

    public static String getPreviousPage(HttpServletRequest request){
        return request.getHeader("Referer");
    }
}
