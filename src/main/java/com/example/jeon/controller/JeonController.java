package com.example.jeon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/public")
public class JeonController {

    @GetMapping("/start")
    public String jeon(Model model) {
        model.addAttribute("data", "jeon start!");
        return "hello";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("data", "login start!");
        return "login";
    }

}
