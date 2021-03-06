package com.epam.training.sportsbetting.domain;

import java.time.LocalDateTime;
import java.util.List;

public class TenisSportEvent extends SportEvent {

    public TenisSportEvent(String title, LocalDateTime startDate, LocalDateTime endDate, List<Bet> bets, Result result, String player1, String player2) {
        super(title, startDate, endDate, bets, result, player1, player2);
    }

}
