package com.epam.training.sportsbetting.domain;

import java.time.LocalDate;
import java.util.List;

public class SportEvent implements FootballSportEvent, TenisSportEvent {

    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<Bet> bets;
    private Result result;
    
    
    public SportEvent(String title, LocalDate startDate, LocalDate endDate, List<Bet> bets, Result result) {
        super();
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.bets = bets;
        this.result = result;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public LocalDate getStartDate() {
        return startDate;
    }


    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }


    public LocalDate getEndDate() {
        return endDate;
    }


    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }


    public Result getResult() {
        return result;
    }


    public void setResult(Result result) {
        this.result = result;
    }


    public List<Bet> getBets() {
        return bets;
    }
    
    
    
}
