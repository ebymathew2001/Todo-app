package com.example.springboot.myfristwebapp.hello;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SayHello {



    @RequestMapping("say-hello")
    @ResponseBody
    public String sayHello(){

        return "hello what are you learing today";
    }




        @RequestMapping("say-hello-html")
        @ResponseBody
        public String sayHelloHtml(){

          StringBuffer sb=new StringBuffer();
            sb.append("<html>");
            sb.append("<head>");
            sb.append("<title> My frist HTML page</title>");
            sb.append("</head>");
            sb.append("<body>");
            sb.append("My frist html page with body");
            sb.append("</body>");
            sb.append("</html>");
           return sb.toString();
        }

    @RequestMapping("say-hello-jsp")
    public String sayHelloJsp(){

        return "sayHello";
    }





    }
