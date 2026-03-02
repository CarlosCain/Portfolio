package com.gcu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller for handling requests to the home page.
 */
@Controller
public class HomeController {

    /**
     * Displays the home page.
     *
     * @param model the model to add attributes to
     * @return the name of the view to render
     */
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Home");
        return "home"; // This will refer to the home.html template
    }
}