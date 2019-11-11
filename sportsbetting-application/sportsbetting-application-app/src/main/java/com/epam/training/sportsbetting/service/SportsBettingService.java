package com.epam.training.sportsbetting.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.transaction.Transactional;

import org.springframework.transaction.PlatformTransactionManager;

import com.epam.training.sportsbetting.domain.Outcome;
import com.epam.training.sportsbetting.domain.Player;
import com.epam.training.sportsbetting.domain.SportEvent;
import com.epam.training.sportsbetting.domain.Wager;
import com.epam.training.sportsbetting.domain.SportEventBuilder;

public class SportsBettingService implements Service {

    private Player player;
    private List<SportEvent> sportEvents;
    private List<Wager> wagers;
    
    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;
    
    @Inject
    PlatformTransactionManager txManger;

    public SportsBettingService() {
//        txManger.getTransaction(entityManager.getTransaction());
        SportEventBuilder seb = new SportEventBuilder();
        SportEvent event = seb
                .setTitle("Lakers vs. Celtics")
                .setStartDate(LocalDateTime.parse("2020-02-07T12:00:00"))
                //.setResult(null)
                .setEndDate(LocalDateTime.parse("2020-02-07T13:00:00"))
                .setPlayer1("Lakers")
                .setPlayer2("Celtics")
                .setBets()
                .getInstance();
        
        
        sportEvents = new ArrayList<SportEvent>();
        sportEvents.add(event);
        wagers = new ArrayList<Wager>();
    }

    @Override
    public void savePlayer(Player player) {
        this.player = player;
        entityManager.persist(player);

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
        entityManager.persist(wager);

    }

    @Override
    public List<Wager> findAllWagers() {
        return wagers;
    }

    @Override
    @Transactional
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
            entityManager.persist(sportEvents.get(0));
    }
    
    @Override
    public boolean sufficientBalance(BigDecimal wagerAmount) {
        if (player.getBalance().compareTo(wagerAmount) == -1) {
            return false;
        }
        return true;
    }

}
