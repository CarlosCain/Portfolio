package com.gcu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.validation.Valid;

import com.gcu.model.RegisterModel;
import com.gcu.model.User;
import com.gcu.business.UserService;

/**
 * Controller for handling user registration requests.
 */
@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private UserService userService;

    /**
     * Displays the registration form.
     *
     * @param model the model to add attributes to
     * @return the name of the view to render
     */
    @GetMapping("/")
    public String display(Model model) {
        model.addAttribute("title", "Registration");
        model.addAttribute("registerModel", new RegisterModel());
        return "registration";
    }

    /**
     * Handles the submission of the registration form.
     *
     * @param user the registration form data
     * @param bindingResult the binding result
     * @param model the model to add attributes to
     * @return the name of the view to render
     */
    @PostMapping("/doRegistration")
    public String doRegistration(@Valid @ModelAttribute("registerModel") RegisterModel user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors() || !user.getPassword().equals(user.getConfirmPassword())) {
            model.addAttribute("error", "Password and Confirm Password must match.");
            model.addAttribute("title", "Registration Form");
            return "registration";
        }

        User newUser = new User(null, user.getUsername(), user.getPassword(), true, user.getFirstName(), user.getLastName(), user.getEmail(), user.getAddress(), user.getPhone());

        if (userService.registerUser(newUser)) {
            return "redirect:/login";
        } else {
            model.addAttribute("error", "Registration failed. Please try again.");
            model.addAttribute("title", "Registration Form");
            return "registration";
        }
    }
}