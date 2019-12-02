package com.epam.training.sportsbetting.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

import com.epam.training.sportsbetting.domain.Login;
import com.epam.training.sportsbetting.domain.ModifyPlayerRequest;
import com.epam.training.sportsbetting.domain.Outcome;
import com.epam.training.sportsbetting.domain.OutcomeOdd;
import com.epam.training.sportsbetting.domain.Player;
import com.epam.training.sportsbetting.domain.SportEvent;
import com.epam.training.sportsbetting.domain.SportEventBuilder;
import com.epam.training.sportsbetting.domain.Wager;
import com.epam.training.sportsbetting.repository.OutcomeOddRepository;
import com.epam.training.sportsbetting.repository.PlayerRepository;
import com.epam.training.sportsbetting.repository.SportEventRepository;
import com.epam.training.sportsbetting.repository.WagerRepository;

public class SportsBettingService implements Service {

    private Player player;
    private List<SportEvent> sportEvents;
    private List<Wager> wagers;

    private static final Logger LOG = LoggerFactory.getLogger(SportsBettingService.class);

    @Inject
    private PlayerRepository playerRepository;

    @Inject
    private WagerRepository wagerRepository;

    @Inject
    private SportEventRepository sportEventRepository;

    @Inject
    private OutcomeOddRepository outcomeOddRepository;

    @Inject
    PlatformTransactionManager txManger;

    public SportsBettingService() {
        // txManger.getTransaction(entityManager.getTransaction());
        SportEventBuilder seb = new SportEventBuilder();
        SportEvent event = seb
                .setTitle("Lakers vs. Celtics")
                .setStartDate(LocalDateTime.parse("2020-02-07T12:00:00"))
                // .setResult(null)
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
        if (playerRepository.findByEmail(player.getEmail()).isEmpty()) {
            playerRepository.save(player);
        }
        return;
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
    @Transactional
    public void saveWager(Wager wager) {
        if (wager.getAmount().compareTo(player.getBalance()) == 1) {
            System.out.printf("You don't have enough money, your balance is %d HUF", player.getBalance());
            return;
        }
        wagers.add(wager);
        player.setBalance(player.getBalance().subtract(wager.getAmount()));
        System.out.println("OutcomeOdd id of wager:" + wager.getOutcomeOddId());

        final Optional<Player> findById = playerRepository.findById(wager.getPlayer().getId());
        if (findById.isPresent())
            wager.setPlayer(findById.get());
        Optional<OutcomeOdd> outcomeOdd = outcomeOddRepository.findById(wager.getOutcomeOddId());
        if (outcomeOdd.isPresent())
            wager.setOutcomeOdd(outcomeOdd.get());
        wagerRepository.save(wager);

    }

    @Override
    public List<Wager> findAllWagers() {
        return (List<Wager>) wagerRepository.findAll();
    }

    @Override
    public List<Wager> findWagersbyPlayerId(int playerId) {
        List<Wager> wagers = new ArrayList<>();
        for (Wager wager : wagerRepository.findAll()) {
            if (wager.getPlayer().getId() == playerId)
                wagers.add(wager);
        }
        return wagers;
    }

    @Override
    @Transactional
    public void removeWagerbyId(int id) {
        Optional<Wager> wager = wagerRepository.findById(id);
        boolean deleteable = false;
        if (wager.isPresent()) {
            wager.get().setPlayer(null);
            wager.get().setOutcomeOdd(null);
            for (SportEvent event : sportEventRepository.findAll()) {
                if (event.getStartDate().compareTo(LocalDateTime.now()) > 0)
                    deleteable = true;
            }
        }
        if (deleteable)
            wagerRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void calculateResult() {
        Random r = new Random();
        for (Wager wager : wagers) {
            if (r.nextBoolean()) {
                Outcome outcome = wager.getOutcome();
                SportEvent sportEvent = wager.getSportEvent();
                sportEvent.addWinnerOutcome(outcome);
                wager.setWin(true);
                wager.increasePlayerBalanace(wager.getAmount().multiply(wager.getDecimalOutcomeOdd()));
            }
            wager.setProcessed(true);
        }
        sportEventRepository.saveAll(sportEvents);
    }

    @Override
    public boolean sufficientBalance(BigDecimal wagerAmount) {
        if (player.getBalance().compareTo(wagerAmount) == -1) {
            return false;
        }
        return true;
    }

    @Override
    public Player validateUser(Login login) {
        for (Player player : playerRepository.findAll()) {
            if (login != null && login.getPassword().contentEquals(player.getPassword()) && login.getUsername().contentEquals(player.getEmail())) {
                LOG.info("Player validated: " + player.getName());
                this.player = player;
                player.setCurrencyName(player.getCurrency());
                return player;
            }
        }
        return null;
    }

    @Override
    @Transactional
    public void updatePlayer(ModifyPlayerRequest player) {
        this.player.setName(player.getName());
        this.player.setAccountNumber(player.getAccountNumber());
        this.player.setEmail(player.getEmail());
        this.player.setBirth(player.getBirth());
        this.player.setCurrency(player.getCurrency());
        this.player.setBalance(player.getBalance());
        playerRepository.save(this.player);

    }

}
