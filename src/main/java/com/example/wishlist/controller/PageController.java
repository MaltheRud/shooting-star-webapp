package com.example.wishlist.controller;

import com.example.wishlist.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shootingstar")
public class PageController {
    private UserService service;

    public PageController (UserService service){
        this.service = service;
    }

    @GetMapping("/")
    public String homepage(){
        return "homepage";
    }

    @GetMapping("/signup")
    public String signup(){
        return "signup";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard(){
        return "dashboard";
    }
}
