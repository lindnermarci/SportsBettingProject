package com.epam.training.sportsbetting.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.epam.training.sportsbetting.domain.Outcome;
import com.epam.training.sportsbetting.domain.Player;
import com.epam.training.sportsbetting.domain.SportEvent;
import com.epam.training.sportsbetting.domain.Wager;
import com.epam.training.sportsbetting.domain.SportEventBuilder;

public class SportsBettingService implements Service {

    private Player player;
    private List<SportEvent> sportEvents;
    private List<Wager> wagers;

    public SportsBettingService() {
        SportEventBuilder seb = new SportEventBuilder();
        SportEvent event = seb
                .setTitle("Lakers vs. Celtics")
                .setStartDate(LocalDateTime.parse("2020-02-07T12:00:00"))
                .setResult(null)
                .setEndDate(LocalDateTime.parse("2020-02-07T13:00:00"))
                .setPlayer1("Arsenal")
                .setPlayer2("Chelsea")
                .setBets()
                .getInstance();
        
        
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
        Random r = new Random();
            for(Wager wager: wagers) {
                if(r.nextBoolean()) {
                    Outcome outcome = wager.getOutcome();
                    SportEvent sportEvent = wager.getSportEvent();
                    sportEvent.addWinnerOutcome(outcome);
                    wager.setWin(true);
                    wager.increasePlayerBalanace(wager.getAmount().multiply(wager.getOutcomeOdd()));
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
