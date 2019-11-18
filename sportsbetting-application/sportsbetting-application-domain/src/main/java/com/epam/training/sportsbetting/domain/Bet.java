package com.epam.training.sportsbetting.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Bet {

    @Id
    @GeneratedValue
    private int id;
    private String description;
    @OneToOne(targetEntity = SportEvent.class, cascade=CascadeType.ALL)
    private SportEvent sportEvent;
    private BetType type;
    @OneToMany(targetEntity = Outcome.class, mappedBy = "bet", cascade=CascadeType.ALL)
    private List<Outcome> outcomes;
    

    public Bet() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Bet(String description, SportEvent sportevent, BetType type, List<Outcome> outcomes) {
        super();
        this.description = description;
        this.sportEvent = sportevent;
        this.type = type;
        this.outcomes = outcomes;
    }
    
    public Bet(String description, SportEvent sportevent, BetType type) {
        super();
        this.description = description;
        this.sportEvent = sportevent;
        this.type = type;
        this.outcomes = new ArrayList<Outcome>();
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
    
    public void setOutcomeOdd(OutcomeOdd outcomeOdd, int indexOfOutcome) {
        if(outcomes.size() < indexOfOutcome + 1) {
           System.out.println("Error in Bet.java line 57");
            return;
        }
        outcomes.get(indexOfOutcome).addOutcomeOdd(outcomeOdd);
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
}
