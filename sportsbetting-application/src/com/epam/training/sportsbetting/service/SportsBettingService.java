package com.epam.training.sportsbetting.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.epam.training.sportsbetting.domain.Bet;
import com.epam.training.sportsbetting.domain.BetType;
import com.epam.training.sportsbetting.domain.Outcome;
import com.epam.training.sportsbetting.domain.Player;
import com.epam.training.sportsbetting.domain.SportEvent;
import com.epam.training.sportsbetting.domain.Wager;

public class SportsBettingService implements Sevice {

    private Player player;
    private List<SportEvent> sportEvents;
    private List<Wager> wagers;

    public SportsBettingService() {
        List<Bet> bets = new ArrayList<Bet>();
        
        SportEventBuilder sef = new SportEventBuilder();
        SportEvent event = sef.setTitle("Lakers vs. Celtics").setStartDate(LocalDateTime.parse("2020-02-07T12:00:00")).setResult(null).setEndDate(LocalDateTime.parse("2020-02-07T13:00:00")).setPlayer1("Arsenal").setPlayer2("Chelsea").getInstance();
        
        
        
        BetBuilder betBuilder = new BetBuilder();
        
        final LocalDateTime validFrom = LocalDateTime.parse("2000-01-01T12:00:00");
        final LocalDateTime validUntil = LocalDateTime.parse("2020-02-07T12:00:00");
        OutcomeOddBuilder outcomeOddBuilder = new OutcomeOddBuilder();
        outcomeOddBuilder.setValue(BigDecimal.valueOf(4)).setValidForm(validFrom).setValidUntil(validUntil);
        
        
        bets.add(betBuilder.setDescription("player Lebron James score")
                .setSportevent(event)
                .setType(BetType.PLAYERS_SCORE)
                .addOutcome(new Outcome("28", betBuilder.GetInstance()))
                .setOutcomeOdd(outcomeOddBuilder.getInstance(),0)
                .GetInstance());
        
        
        betBuilder = new BetBuilder();
        bets.add(betBuilder.setDescription("number of scored goals")
                .setSportevent(event)
                .setType(BetType.GOALS)
                .addOutcome(new Outcome("129", betBuilder.GetInstance()))
                .setOutcomeOdd(outcomeOddBuilder.setValue(BigDecimal.valueOf(3)).getInstance(),0)
                .GetInstance());
        
        betBuilder = new BetBuilder();
        bets.add(betBuilder.setDescription("winner")
                .setSportevent(event)
                .setType(BetType.WINNER)
                .addOutcome(new Outcome("Lakers", betBuilder.GetInstance()))
                .setOutcomeOdd(outcomeOddBuilder.setValue(BigDecimal.valueOf(2)).getInstance(),0)
                .GetInstance());
        
        betBuilder = new BetBuilder();
        bets.add(betBuilder.setDescription("winner")
                .setSportevent(event)
                .setType(BetType.WINNER)
                .addOutcome(new Outcome("Celtics", betBuilder.GetInstance()))
                .setOutcomeOdd(outcomeOddBuilder.setValue(BigDecimal.valueOf(2)).getInstance(),0)
                .GetInstance());
                
        
        event.setBets(bets);
        sportEvents = new ArrayList<SportEvent>();
        sportEvents.add(event);
        wagers = new ArrayList<Wager>();
    }

    @Override
    public void savePlayer(Player player) {
        this.player = player;

    }

    @Override
    public Player findPlayer() {
        return player;
    }

    @Override
    public List<SportEvent> findAllSportEvents() {
        return sportEvents;
    }

    @Override
    public void saveWager(Wager wager) {
        if (wager.getAmount().compareTo(player.getBalance()) == 1) {
            System.out.printf("You don't have enough money, your balance is %d HUF", player.getBalance());
            return;
        }
        wagers.add(wager);
        player.setBalance(player.getBalance().subtract(wager.getAmount()));

    }

    @Override
    public List<Wager> findAllWagers() {
        return wagers;
    }

    @Override
    public void calculateResult() {
        int counter = 0;
            for(Wager wager: wagers) {
                if(counter % 2 == 0) {
                    Outcome outcome = wager.getOutcome();
                    SportEvent sportEvent = wager.getSportEvent();
                    sportEvent.addWinnerOutcome(outcome);
                    wager.setWin(true);
                    wager.increasePlayerBalanace(wager.getAmount().multiply(wager.getOutcomeOdd()));
                    counter++;
                }
                wager.setProcessed(true);
            }
    }
    
    @Override
    public boolean sufficientBalance(BigDecimal wagerAmount) {
        if (player.getBalance().compareTo(wagerAmount) == -1) {
            return false;
        }
        return true;
    }

}
