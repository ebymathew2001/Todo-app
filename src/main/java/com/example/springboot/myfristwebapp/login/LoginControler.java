package com.example.springboot.myfristwebapp.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class LoginControler {


    private Authentication authentication;

    public LoginControler(Authentication authentication) {
        this.authentication = authentication;
    }


    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginControler() {

        return "loginControler";
    }


    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String gotoWelcomepage(@RequestParam String name, @RequestParam String password, ModelMap model) {
        if (authentication.authenticate(name,password)) {
            model.put("name", name);


            return "welcom";
        }
        model.put("errorMessage","invalid creadentials plsese try again");
        return "loginControler";

    }
}
