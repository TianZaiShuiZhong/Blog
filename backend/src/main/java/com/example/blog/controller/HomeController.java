package com.example.blog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Blog Backend is running! Access APIs at /api/posts or /api/photos. Visit http://localhost:5173 for the frontend.";
    }
}
