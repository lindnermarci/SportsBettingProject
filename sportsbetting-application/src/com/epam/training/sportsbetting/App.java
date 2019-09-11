package com.epam.training.sportsbetting;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

import com.epam.training.sportsbetting.domain.Currency;
import com.epam.training.sportsbetting.domain.OutcomeOdd;
import com.epam.training.sportsbetting.domain.Player;
import com.epam.training.sportsbetting.domain.Wager;
import com.epam.training.sportsbetting.service.SportsBettingSevice;
import com.epam.training.sportsbetting.view.View;

public class App {
    
    private static SportsBettingSevice service;
    private static View view;
    
    public static void main(String[] args) {
        createPLayer();
        play();
    }
    
    public App(SportsBettingSevice sportsBettingSevice, View view) {
        App.service = sportsBettingSevice;
        App.view = view;
    }
    
    public static void play() {
        Player player = service.findPlayer();
        Scanner scanner = new Scanner(System.in);
        
        view.PrintWelcomeMessage(player);
        String in = scanner.nextLine();
        boolean quit = false;
        
        while(true) {
            view.PrintBalanace(player);
            view.PrintOutcomeOdds(service.findAllSportEvents());
            OutcomeOdd outcomeOdd = view.selectOutcomeOdd(service.findAllSportEvents());
            if (outcomeOdd == null) break;
            
            BigDecimal wagerAmount = view.readWagerAmount();
            Wager wager = null;
            if(service.sufficientBalance(wagerAmount)) {
                wager = new Wager(wagerAmount, outcomeOdd, player);
                service.saveWager(wager);
            }else {
                do {
                    view.printNotEnoughBalance(player);
                    wagerAmount = view.readWagerAmount();
                }while(!service.sufficientBalance(wagerAmount));
                wager = new Wager(wagerAmount, outcomeOdd, player);
            }
            view.printWagerSaved(wager);
        }
        
        view.printResult(player, service.findAllWagers());
        

    }
    
    private static void createPLayer() {
        service.savePlayer(view.readPlayerData());
    }
    
    private void calculateResult() {
        
    }
    
    private void printResult() {
        
    }
}
