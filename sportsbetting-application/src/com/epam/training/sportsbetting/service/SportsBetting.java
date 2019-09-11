package com.epam.training.sportsbetting.service;

import java.math.BigDecimal;
import java.util.List;

import com.epam.training.sportsbetting.domain.Player;
import com.epam.training.sportsbetting.domain.SportEvent;
import com.epam.training.sportsbetting.domain.Wager;

public class SportsBetting implements SportsBettingSevice {

    private Player player;
    private List<SportEvent> sportEvents;
    private List<Wager> wagers;
    
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
        if(wager.getAmount().compareTo(player.getBalance()) == 1) {
            System.out.printf("You don't have enough money, your balance is %d HUF", player.getBalance());
            return;
        }
        wagers.add(wager);
        
        
    }

    @Override
    public List<Wager> findAllWagers() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void calculateResult() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean sufficientBalance(BigDecimal wagerAmount) {
        if (player.getBalance().compareTo(wagerAmount) == 1) {
            return true;
        }
        return false;
    }

}
