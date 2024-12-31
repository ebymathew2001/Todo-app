package com.example.springboot.myfristwebapp.login;


import org.springframework.stereotype.Service;

@Service
public class Authentication {

   public boolean authenticate(String username,String password){
      boolean isValidusername= username.equalsIgnoreCase("eby");
      boolean isVlidatePassword=password.equalsIgnoreCase("dummy");

      return isValidusername && isVlidatePassword;

   }
}
