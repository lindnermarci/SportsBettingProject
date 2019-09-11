package com.epam.training.sportsbetting.view;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.epam.training.sportsbetting.domain.Bet;
import com.epam.training.sportsbetting.domain.Currency;
import com.epam.training.sportsbetting.domain.Outcome;
import com.epam.training.sportsbetting.domain.OutcomeOdd;
import com.epam.training.sportsbetting.domain.Player;
import com.epam.training.sportsbetting.domain.SportEvent;
import com.epam.training.sportsbetting.domain.Wager;

public class SportsBettingView implements View {

    @Override
    public Player readPlayerData() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What is your name?");
        String name = scanner.nextLine();
        System.out.println("How much money do you have (more than 0)?");
        BigDecimal money = BigDecimal.valueOf(scanner.nextDouble());
        System.out.println("What is your currency? (HUF, EUR or USD)");
        String stringCurrency = scanner.nextLine();
        scanner.close();
        Currency currency = stringCurrency == "HUF" ? Currency.HUF : (stringCurrency == "EUR" ? Currency.EUR : Currency.USD);
        return new Player(name, 1, money, LocalDate.now(), currency);
    }

    @Override
    public void PrintWelcomeMessage(Player player) {
        System.out.printf("Welcome %s!", player.getName());
        
    }

    @Override
    public void PrintBalanace(Player player) {
        System.out.printf("Your balance is %d!", player.getBalance());
    }

    @Override
    public void PrintOutcomeOdds(List<SportEvent> sportEvents) {
        System.out.println("> What do you want to bet on? (choose a number or press q for quit)");
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for(SportEvent event : sportEvents) {
            for(Bet bet: event.getBets()) {
                for(Outcome outcome: bet.getOutcomes()) {
                    for(OutcomeOdd outcomeOdd: outcome.getOutcomeOdds()) {
                        sb.append(i)
                        .append(": sport event: ")
                        .append(event.getTitle())
                        .append(" (")
                        .append(event.getStartDate())
                        .append("), Bet: ")
                        .append(bet.getDescription())
                        .append(", Outcome: ")
                        .append(outcome.getDescription())
                        .append(", Actual odd: ")
                        .append(outcomeOdd.getValue())
                        .append(", Valid between ")
                        .append(outcomeOdd.getValidForm())
                        .append(" and ")
                        .append(outcomeOdd.getValidUntil())
                        .append('.');
                        System.out.println(sb.toString());
                        sb = new StringBuilder();
                        i++;
                    }
                }
            }
        }
        
    }

    @Override
    public OutcomeOdd selectOutcomeOdd(List<SportEvent> sportEvents) {
        Scanner scanner = new Scanner(System.in);
        String selected = scanner.nextLine();
        if(selected.contentEquals("q")) {
            scanner.close();
            return null;
        }
        int selectedIndex = Integer.parseInt(selected);
        scanner.close();
        int i = 1;
        for(SportEvent event : sportEvents) {
            for(Bet bet: event.getBets()) {
                for(Outcome outcome: bet.getOutcomes()) {
                    for(OutcomeOdd outcomeOdd: outcome.getOutcomeOdds()) {
                        if(i == selectedIndex) {
                            return outcomeOdd;
                        }
                        i++;
                    }
                }
            }
        }
        return null;
        
    }

    @Override
    public BigDecimal readWagerAmount() {
        System.out.println("> What amount do you wish to bet on it?");
        Scanner scanner = new Scanner(System.in);
        BigDecimal wager = scanner.nextBigDecimal();
        scanner.close();
        return wager;
    }

    @Override
    public void printWagerSaved(Wager wager) {
        
        StringBuilder sb = new StringBuilder();
        sb.append("> Wager \'")
        .append(wager.getOdd().getOutcomeDescription())
        .append("\' of ")
        .append(wager.getSportEventTitle())
        .append(" [odd: ")
        .append(wager.getOdd().getValue())
        .append(", amount: ")
        .append(wager.getAmount())
        .append(" saved!");
        System.out.println(sb.toString());
    }

    @Override
    public void printNotEnoughBalance(Player player) {
        System.out.printf("You don't have enough money, your balance is %d HUF", player.getBalance());
        
    }

    @Override
    public void printResult(Player player, List<Wager> wagers) {
        // TODO Auto-generated method stub
        
    }

}
