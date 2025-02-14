package com.example.Todo_App.repository;

import com.example.Todo_App.entity.Todo;
import com.example.Todo_App.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByUser(User user);// Fetch todos for a specific user
}
