package com.test.prueba.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

      @GetMapping("/home")
    public String index(){
          return "home";
      }

      @GetMapping("/home/informacion")
    public String info(){
          return "info";
      }

      @GetMapping("home/contacto")
    public String contac(){
          return "contacto";
      }

      @GetMapping("home/login")
    public String login(){
          return "login";
      }
}
