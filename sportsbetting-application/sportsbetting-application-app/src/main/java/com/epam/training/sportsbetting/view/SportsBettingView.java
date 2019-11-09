package com.epam.training.sportsbetting.view;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epam.training.sportsbetting.domain.Bet;
import com.epam.training.sportsbetting.domain.Currency;
import com.epam.training.sportsbetting.domain.Outcome;
import com.epam.training.sportsbetting.domain.OutcomeOdd;
import com.epam.training.sportsbetting.domain.Player;
import com.epam.training.sportsbetting.domain.SportEvent;
import com.epam.training.sportsbetting.domain.Wager;

public class SportsBettingView implements View {

    private Scanner scanner = new Scanner(System.in);
    private static final Logger LOG = LoggerFactory.getLogger(SportsBettingView.class);

    @Override
    public Player readPlayerData() {
        System.out.println("> What is your name?");
        String name = "";
        name = scanner.nextLine();            
        while(name.isEmpty()) {
           LOG.error("You have entered an empty string!");
            name = scanner.nextLine();            
        }
        System.out.println("> How much money do you have (more than 0)?");
        BigDecimal money = null;
        String amount = scanner.nextLine();
        while(amount.isEmpty() || amount.matches("[\\D]+")) {
            LOG.error("Invalid Input!");
            amount = scanner.nextLine();
        }
        money = BigDecimal.valueOf(Double.parseDouble(amount));    
        
        while(money == null || isNotPositive(money)) {
            LOG.error("Invalid input!");
            amount = scanner.nextLine();
            money = BigDecimal.valueOf(Double.parseDouble(amount));            
        }
        System.out.println("> What is your currency? (HUF, EUR or USD)");
        String stringCurrency = scanner.nextLine();

        Currency currency;
        switch(stringCurrency) 
        { 
            case "HUF": 
                currency = Currency.HUF;
                break; 
            case "EUR": 
                currency = Currency.HUF;
                break; 
            case "USD": 
                currency = Currency.USD; 
                break; 
            default:
                LOG.info("Could not parse input. Currency set to default value(USD).");
                currency = Currency.USD;
        } 
        return new Player(name, 1, money, LocalDate.now(), currency);
    }

    private boolean isNotPositive(BigDecimal money) {
        return money.compareTo(new BigDecimal(1)) == -1;
    }

    @Override
    public void PrintWelcomeMessage(Player player) {
        System.out.println("> Welcome " + player.getName() + "!");

    }

    @Override
    public void PrintBalanace(Player player) {
        System.out.printf("> Your balance is " + formatCurrency(player.getBalance().intValue(), player.getCurrency()));
        System.out.println();
    }

    private String formatCurrency(int value, Currency currency) {
        final String CURRENCY_FORMAT_PATTERN = "###,###.###";

        DecimalFormat decimalFormat = new DecimalFormat(CURRENCY_FORMAT_PATTERN);

        return decimalFormat.format(value) + " " + currency.toString();
    }

    @Override
    public void PrintOutcomeOdds(List<SportEvent> sportEvents) {
        System.out.println("> What do you want to bet on? (choose a number or press q for quit)");
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (SportEvent event : sportEvents) {
            for (Bet bet : event.getBets()) {
                for (Outcome outcome : bet.getOutcomes()) {
                    for (OutcomeOdd outcomeOdd : outcome.getOutcomeOdds()) {
                        sb.append("> ")
                                .append(i)
                                .append(": Sport event: ")
                                .append(event.getTitle())
                                .append(" (start: ")
                                .append(event.getStringStartDate())
                                .append("), Bet: ")
                                .append(bet.getDescription())
                                .append(", Outcome: ")
                                .append(outcomeOdd.getOutcomeDescription())
                                .append(", Actual odd: ")
                                .append(outcomeOdd.getValue())
                                .append(", Valid between ")
                                .append(outcomeOdd.getStringValidForm())
                                .append(" and ")
                                .append(outcomeOdd.getStringValidUntil())
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
    public Optional<OutcomeOdd> selectOutcomeOdd(List<SportEvent> sportEvents) {
        String selected = scanner.nextLine();
        while(!(selected.matches("\\d") || selected.contentEquals("q"))) {
            LOG.error("Invalid input!");
            selected = scanner.nextLine();            
        }
            if (selected.contentEquals("q")) {
                scanner.close();
                return Optional.empty();
            }
            int selectedIndex = Integer.parseInt(selected);
            int i = 1;
            for (SportEvent event : sportEvents) {
                for (Bet bet : event.getBets()) {
                    for (Outcome outcome : bet.getOutcomes()) {
                        for (OutcomeOdd outcomeOdd : outcome.getOutcomeOdds()) {
                            if (i == selectedIndex) {
                                return Optional.of(outcomeOdd);
                            }
                            i++;
                        }
                    }
                }
            }
        LOG.error("You have chosen a bet that does not exists, please give a valid number.");
        selectOutcomeOdd(sportEvents);
        return null;
    }

    @Override
    public BigDecimal readWagerAmount() {
        System.out.println("> What amount do you wish to bet on it?");
        String next = scanner.nextLine();
        BigDecimal wager = BigDecimal.valueOf(Double.parseDouble(next));
        while(isNotPositive(wager)) {
            System.out.println("Invalid input");
            wager = BigDecimal.valueOf(Double.parseDouble(next));
        }
        return wager;
    }

    @Override
    public void printWagerSaved(Wager wager) {

        StringBuilder sb = new StringBuilder();
        sb.append("> Wager \'")
                .append(wager.getBetDescription())
                .append("=")
                .append(wager.getOutcomeDescription())
                .append("\' of ")
                .append(wager.getSportEventTitle())
                .append(" [odd: ")
                .append(wager.getOdd().getValue())
                .append(", amount: ")
                .append(wager.getAmount().intValue())
                .append("] saved!");
        System.out.println(sb.toString());
    }

    @Override
    public void printNotEnoughBalance(Player player) {
        LOG.info("> You don't have enough money, your balance is " + formatCurrency(player.getBalance().intValue(), player.getCurrency()));
        System.out.println();

    }

    @Override
    public void printResult(Player player, List<Wager> wagers) {
        System.out.println("> Results:");
        StringBuilder sb = new StringBuilder();
        for (Wager wager : wagers) {
            sb.append("> Wager \'")
                    .append(wager.getBetDescription())
                    .append("=")
                    .append(wager.getOutcomeDescription())
                    .append("\' of ")
                    .append(wager.getSportEventTitle())
                    .append(" [odd: ")
                    .append(wager.getOdd().getValue())
                    .append(", amount: ")
                    .append(wager.getAmount().intValue())
                    .append("], win: ")
                    .append(wager.isWin());

            System.out.println(sb.toString());
            sb = new StringBuilder();
        }

        System.out.println("> Your new balance is " + formatCurrency(player.getBalance().intValue(), player.getCurrency()) + '.');
    }

}
