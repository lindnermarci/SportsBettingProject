package com.epam.training.sportsbetting.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;

public class FootballSportEvent extends SportEvent {

    public FootballSportEvent(String title, LocalDateTime startDate, LocalDateTime endDate, List<Bet> bets, Result result, String player1, String player2) {
        super(title, startDate, endDate, bets, result, player1, player2);
    }

}
