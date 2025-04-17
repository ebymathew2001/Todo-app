package com.example.Todo_App.controller;

import com.example.Todo_App.entity.User;
import com.example.Todo_App.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
        try{
            userService.registerUser(user);
            redirectAttributes.addFlashAttribute("success", "Registration successful! You can now log in.");
            return "redirect:/login";
        }
        catch(IllegalArgumentException e){
            redirectAttributes.addFlashAttribute("error",e.getMessage());// Pass the error message to the view
            return "redirect:/signup";
        }


    }

    @GetMapping("/login")
    public String showLoginPage(HttpSession session, @RequestParam(required = false) String error) {
        // If there's an error parameter, allow access to show the error
        if (error != null) {
            return "login";
        }
        // Check if user is already logged in
        if (session.getAttribute("admin") != null) {
            return "redirect:/adminhome";
        }
        if (session.getAttribute("user") != null) {
            return "redirect:/customerhome";
        }

        // Not logged in, show login page
        return "login";
    }


    @GetMapping("/customerhome")
    public String showCustomerHome(Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());  // Get logged-in username
        }
        return "customerhome";
    }

}




