package com.epam.training.sportsbetting.service;

import java.util.ArrayList;
import java.util.List;

import com.epam.training.sportsbetting.domain.Bet;
import com.epam.training.sportsbetting.domain.BetType;
import com.epam.training.sportsbetting.domain.Outcome;
import com.epam.training.sportsbetting.domain.OutcomeOdd;
import com.epam.training.sportsbetting.domain.SportEvent;

public class BetBuilder {
    private String description;
    private SportEvent sportEvent;
    private BetType type;
    List<Outcome> outcomes;
    private Bet bet;
    
    public BetBuilder() {
        this.outcomes = new ArrayList<Outcome>();
    }
    
    
    
    public BetBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public BetBuilder setSportevent(SportEvent sportevent) {
        this.sportEvent = sportevent;
        return this;
    }

    public BetBuilder setType(BetType type) {
        this.type = type;
        return this;
    }
    
    public BetBuilder addOutcome(Outcome outcome) {
        outcomes.add(outcome);
        return this;
    }
    
    public BetBuilder setOutcomeOdd(OutcomeOdd outcomeOdd, int indexOfOutcome) {
        if(outcomes.size() < indexOfOutcome + 1) {
           System.out.println("Error in BetBuilder, Array out of bounds");
            return this;
        }
        Outcome outcome = outcomes.get(indexOfOutcome);
        outcomeOdd.setOutcome(outcome);
        outcome.addOutcomeOdd(outcomeOdd);
        return this;
    }

    
    public Bet GetInstance() {
        if(bet != null) {return bet; }
        bet = new Bet(description, sportEvent, type, outcomes);
        return bet;
    }
}
