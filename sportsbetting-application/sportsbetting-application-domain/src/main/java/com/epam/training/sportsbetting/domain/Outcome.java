package com.epam.training.sportsbetting.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Outcome {
    @Id
    @GeneratedValue
    private int id;
    private String description;
    @OneToOne(targetEntity = Bet.class, cascade=CascadeType.ALL)
    private Bet bet;
    @OneToMany(targetEntity = OutcomeOdd.class, cascade=CascadeType.ALL)
    List<OutcomeOdd> outcomeOdds;
    
    public Outcome(String description, Bet bet, List<OutcomeOdd> outcomeOdds) {
        super();
        this.description = description;
        this.bet = bet;
        this.outcomeOdds = outcomeOdds;
    }
    
    public Outcome(String description, Bet bet) {
        super();
        this.description = description;
        this.bet = bet;
        this.outcomeOdds = new ArrayList<OutcomeOdd>();
    }
    
    
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Bet getBet() {
        return bet;
    }
    public void setBet(Bet bet) {
        this.bet = bet;
    }
    public List<OutcomeOdd> getOutcomeOdds() {
        return outcomeOdds;
    }
    
    public String getSportEventTitle() {
        return bet.getSportEventTitle();
    }


    public void addOutcomeOdd(OutcomeOdd outcomeOdd) {
        outcomeOdds.add(outcomeOdd);
        
    }

    public String getBetDescription() {
        return bet.getDescription();
    }

    public SportEvent getSportEvent() {
        return bet.getSportevent();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}
