package com.gcu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ConfirmationController {

    @GetMapping("/confirmation")
    public String showConfirmation() {
        return "confirmation"; // loads confirmation.html
    }
}