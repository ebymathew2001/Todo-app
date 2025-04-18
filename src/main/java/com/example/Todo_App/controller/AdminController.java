package com.example.Todo_App.controller;

import com.example.Todo_App.entity.User;
import com.example.Todo_App.service.AdminService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;
    private Long id;
    private Model model;


    @GetMapping("admin-home")
    public String showAdminHome(){
        return "/admin-home";
    }
    @GetMapping("/dashboard")
    public String adminDashboard(Model model, HttpSession session){
        if(session.getAttribute("admin")==null){
            return "redirect:/login";
        }
        List<User> users=adminService.getAllUsers();
        model.addAttribute("users",users);
        return "admin-dashboard";
    }
    @GetMapping("/search")
    public String searchUsers(@RequestParam String keyword, Model model){
        List<User> users =adminService.searchUsersByName(keyword);
        model.addAttribute("users",users);
        return "admin-dashboard";
    }

    @GetMapping("/adduser")
    public String addUserView(Model model){
        System.out.println("Inside addUserView method");
        model.addAttribute("user",new User());
        return "adduser";
    }
    @PostMapping("/adduser")
    public String saveUser(@ModelAttribute("user") User user){
        adminService.saveUser(user);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/edit/{id}")
    public String ShowEditForm(@PathVariable("id")Long id, Model model){
        this.id = id;
        this.model = model;
        User user =adminService.getUserById(id);
        model.addAttribute("user",user);
        return "edituser";
    }

    @PostMapping("/update-user")
    public String updateUser(@ModelAttribute("user") User user){
        adminService.updateUser(user);
        return "redirect:/admin/dashboard";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        adminService.deleteUser(id);
        return "redirect:/admin/dashboard";
    }



    
}
