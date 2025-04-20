package com.example.jeon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JeonController {

    @GetMapping("start")
    public String jeon(Model model) {
        model.addAttribute("data", "jeon start!");
        return "hello";
    }

}
