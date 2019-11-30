package com.epam.training.sportsbetting.controllers;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.epam.training.sportsbetting.domain.Login;
import com.epam.training.sportsbetting.domain.ModifyPlayerRequest;
import com.epam.training.sportsbetting.domain.Player;
import com.epam.training.sportsbetting.domain.SportEvent;
import com.epam.training.sportsbetting.domain.Wager;
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
    public String validateUser(@ModelAttribute Login login, Model model) {
        Player player = sportsbettingService.validateUser(login);
        List<Wager> wagers = sportsbettingService.findWagersbyPlayerId(player.getId());
        SportEvent event = sportsbettingService.findAllSportEvents().get(0);
        if (player != null) {
            model.addAttribute("player", player);
            model.addAttribute("event", event);
            model.addAttribute("wagers", wagers);
            System.out.println(wagers.size());
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
    public String addPlayer(@ModelAttribute Player player) {
        sportsbettingService.savePlayer(player);
        return "login";
    }
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String savePlayer(ModifyPlayerRequest modifyPlayerRequest, Model model) {
        sportsbettingService.updatePlayer(modifyPlayerRequest);
        Player player = sportsbettingService.findPlayer();
        List<Wager> wagers = sportsbettingService.findWagersbyPlayerId(player.getId());
        SportEvent event = sportsbettingService.findAllSportEvents().get(0);
        model.addAttribute("player", player);
        model.addAttribute("event", event);
        model.addAttribute("wagers", wagers);
        return "home";
    }
    
    @RequestMapping(value = "/removeWager", method = RequestMethod.POST)
    public void removeWager(@RequestParam("wagerId") int wagerId, Model model) {
        sportsbettingService.removeWagerbyId(wagerId);
        homeScreen(model);
    }
    
    private String homeScreen(Model model) {
        Player player = sportsbettingService.findPlayer();
        List<Wager> wagers = sportsbettingService.findWagersbyPlayerId(player.getId());
        SportEvent event = sportsbettingService.findAllSportEvents().get(0);
        model.addAttribute("player", player);
        model.addAttribute("event", event);
        model.addAttribute("wagers", wagers);
        return "home";
    }
    
    

}
