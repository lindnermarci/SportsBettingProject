package com.epam.training.sportsbetting.controllers;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.epam.training.sportsbetting.domain.Login;
import com.epam.training.sportsbetting.domain.Player;
import com.epam.training.sportsbetting.service.Service;

@Controller
public class HomeController {

    @Inject
    Service sportsbettingService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String login(HttpSession session) {
        session.setAttribute("token", "12345");
        System.out.println("invoking addProject");
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String validateUser(@ModelAttribute Login login) {
        System.out.println(login.getPassword());
        System.out.println(login.getUsername());
        Player player = sportsbettingService.validateUser(login);
        if (player != null) {
            return "home";
        } else {
            return "login";
        }
    }
    
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerPlayer() {
        return "register";
    }
    
    
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String savePlayer(@ModelAttribute Player player) {
        sportsbettingService.savePlayer(player);
        return "home";
    }
    

}
