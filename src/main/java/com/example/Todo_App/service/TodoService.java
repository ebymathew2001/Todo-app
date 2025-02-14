package com.example.Todo_App.service;

import com.example.Todo_App.entity.Todo;
import com.example.Todo_App.entity.User;
import com.example.Todo_App.repository.TodoRepository;
import com.example.Todo_App.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Todo> getTodosByUser(User user) {
        return todoRepository.findByUser(user); // Fetch todos for a specific user
    }



    public void saveTodo(Todo todo) {
        todoRepository.save(todo);
    }
    public Todo getTodoById(Long id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found"));
    }

    public void updateTodo(Todo todo) {
        todoRepository.save(todo); // Save the updated todo
    }

    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }



}
