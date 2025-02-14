package com.example.Todo_App.controller;

import com.example.Todo_App.entity.Todo;
import com.example.Todo_App.entity.User;
import com.example.Todo_App.service.TodoService;
import com.example.Todo_App.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class TodoController {

    @Autowired
    private TodoService todoService;

    @Autowired
    private UserService userService; // Needed to fetch User entity

    @GetMapping("/todos")
    public String listTodos(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login"; // Redirect to login if not authenticated
        }

        // Fetch logged-in user's username
        String username = principal.getName();

        model.addAttribute("username", username);
        // Fetch User entity based on username
        User user = userService.getUserByUsername(username);

        if (user == null) {
            return "redirect:/login"; // Handle case where user is not found
        }

        // Fetch todos for the user
        List<Todo> todos = todoService.getTodosByUser(user);

        model.addAttribute("todos", todos);
        return "todos"; // Refers to todos.html
    }

    @GetMapping("/addtodo")
    public String showAddTodoForm(Model model) {
        model.addAttribute("todo", new Todo());
        return "addtodo";
    }
    @PostMapping("/addtodo")
    public String saveTodo(@ModelAttribute Todo todo, Principal principal) {
        if (principal == null) {
            return "redirect:/login"; // Redirect if not authenticated
        }

        // Fetch the logged-in user
        String username = principal.getName();
        User user = userService.getUserByUsername(username);

        // Assign the user to the todo and save it
        todo.setUser(user);
        todoService.saveTodo(todo);

        return "redirect:/todos"; // Redirect back to the todo list
    }

    @GetMapping("/todo/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Todo todo = todoService.getTodoById(id);
        model.addAttribute("todo", todo);
        return "todoedit"; // Thymeleaf template
    }

    @PostMapping("/todo/edit/{id}")
    public String updateTodo(@PathVariable Long id, @ModelAttribute Todo todo) {
        Todo existingTodo = todoService.getTodoById(id); // Get the existing todo
        todo.setUser(existingTodo.getUser()); // Ensure the user is retained

        todoService.updateTodo(todo);
        return "redirect:/todos";
    }

    @GetMapping("/todo/delete/{id}")
    public String deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return "redirect:/todos";
    }








}
