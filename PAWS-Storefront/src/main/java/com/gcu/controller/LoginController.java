package com.gcu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.business.AuthService;
import com.gcu.model.LoginModel;

/**
 * Controller for handling login requests.
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AuthService authService;

    /**
     * Displays the login page.
     *
     * @param model the model to add attributes to
     * @return the name of the view to render
     */
    @GetMapping
    public String showLoginPage(Model model) {
        model.addAttribute("title", "Login");
        model.addAttribute("loginModel", new LoginModel());
        return "login"; // Ensure this matches the name of your template file
    }

    /**
     * Processes the login form submission.
     *
     * @param loginModel the login form data
     * @param model the model to add attributes to
     * @return the name of the view to render
     */
    @PostMapping
    public String doLogin(LoginModel loginModel, Model model) {
        if (authService.authenticate(loginModel.getUsername(), loginModel.getPassword())) {
            return "redirect:/products"; // Redirect to the products page upon successful login
        } else {
            model.addAttribute("error", "Invalid username and/or password.");
            return "login";
        }
    }
}