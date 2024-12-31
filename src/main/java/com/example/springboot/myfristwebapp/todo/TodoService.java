package com.example.springboot.myfristwebapp.todo;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class TodoService {

    private static List<Todo> todos=new ArrayList<>();

    private static int todosCount=0;
    static {
        todos.add(new Todo(++todosCount,"in28minutes","Learn AWS",
                LocalDate.now().plusYears(1),false));
        todos.add(new Todo(++todosCount,"in28minutes","Learn DevOps",
                LocalDate.now().plusYears(1),false));
        todos.add(new Todo(++todosCount,"in28minutes","Learn Full Stack Development",
                LocalDate.now().plusYears(1),false));
    }

    public List<Todo> findByUserName(String username){
        return todos;
    }

    public void addTodo(String username,String description,LocalDate targetDate,boolean done) {
        Todo todo = new Todo(++todosCount, username, description, targetDate, done);

        todos.add(todo);
    }

    public void delteById(int id){


        Predicate<? super Todo> predicate;
        todos.removeIf(predicate);
    }





}

