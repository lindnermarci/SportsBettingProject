package com.epam.training.sportsbetting.domain;

import java.util.List;

public class Bet {
    private String description;
    private SportEvent sportEvent;
    private BetType type;
    List<Outcome> outcomes;
    
    public Bet(String description, SportEvent sportevent, BetType type, List<Outcome> outcomes) {
        super();
        this.description = description;
        this.sportEvent = sportevent;
        this.type = type;
        this.outcomes = outcomes;
    }
    
    
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public SportEvent getSportevent() {
        return sportEvent;
    }
    public void setSportevent(SportEvent sportevent) {
        this.sportEvent = sportevent;
    }
    public BetType getType() {
        return type;
    }
    public void setType(BetType type) {
        this.type = type;
    }
    public List<Outcome> getOutcomes() {
        return outcomes;
    }
    
    public String getSportEventTitle() {
        return sportEvent.getTitle();
    }

    
}
