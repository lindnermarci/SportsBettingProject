package com.epam.training.sportsbetting.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.epam.training.sportsbetting.domain.Bet;
import com.epam.training.sportsbetting.domain.Result;
import com.epam.training.sportsbetting.domain.SportEvent;

public class SportEventBuilder {
    private static String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private List<Bet> bets;
    private Result result;
    private String player1;
    private String player2;
    
    
    public SportEventBuilder() {
       bets = new ArrayList<Bet>();
       result = new Result();
    }
    public SportEventBuilder setTitle(String title) {
        SportEventBuilder.title = title;
        return this;
    }
    public SportEventBuilder setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
        return this;
    }
    public SportEventBuilder setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
        return this;
    }
    public SportEventBuilder setBets(List<Bet> bets) {
        this.bets = bets;
        return this;
    }
    public SportEventBuilder setResult(Result result) {
        this.result = result;
        return this;
    }
    public SportEventBuilder setPlayer1(String player1) {
        this.player1 = player1;
        return this;
    }
    public SportEventBuilder setPlayer2(String player2) {
        this.player2 = player2;
        return this;
    }
    
    public SportEvent getInstance() {
        return new SportEvent(title, startDate, endDate, bets, result, player1, player2);
    }
}
