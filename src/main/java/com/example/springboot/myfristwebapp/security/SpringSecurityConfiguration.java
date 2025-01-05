package com.example.springboot.myfristwebapp.security;

import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.net.http.HttpClient;
import java.util.function.Function;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SpringSecurityConfiguration {

     @Bean
     public InMemoryUserDetailsManager createUserDetailsManager(){


         UserDetails userDetails1 = createNewUser("eby", "dummy");
         UserDetails userDetails2 = createNewUser("felix", "pongola");

         return new InMemoryUserDetailsManager(userDetails1,userDetails2);

     }

    private UserDetails createNewUser(String username, String password) {
        Function<String, String> passwordEncoder
                =input -> passwordEncoder().encode(input);
        UserDetails userDetails=User.builder()
                                    .passwordEncoder(passwordEncoder)
                                    .username(username)
                                    .password(password)
                                    .roles("USER","ADMIN")
                                    .build();
        return userDetails;
    }

    @Bean
     public PasswordEncoder passwordEncoder(){
         return new BCryptPasswordEncoder();
     }


     @Bean
     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
         http.authorizeHttpRequests(
                 auth -> auth.anyRequest().authenticated());
         http.formLogin(withDefaults())
          .csrf(csrf -> csrf.disable()) // Updated to lambda-based
          .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()));

         return http.build();

     }




}
