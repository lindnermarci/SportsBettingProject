package com.epam.training.sportsbetting.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
public class Result {
    @Id
    @GeneratedValue
    int id;
    @OneToMany(targetEntity = Outcome.class)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Outcome> winnerOutcomes;

    

    public Result(List<Outcome> winnerOutcomes) {
        super();
        this.winnerOutcomes = winnerOutcomes;
    }
    
    public Result() {
        super();
        this.winnerOutcomes = new ArrayList<Outcome>();
    }

    public List<Outcome> getWinnerOutcomes() {
        return winnerOutcomes;
    }

    public void addWinnerOutcome(Outcome outcome) {
        winnerOutcomes.add(outcome);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
 
}
