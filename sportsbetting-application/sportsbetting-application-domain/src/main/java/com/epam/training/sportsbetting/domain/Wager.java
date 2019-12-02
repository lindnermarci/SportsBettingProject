package com.epam.training.sportsbetting.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Wager {
    @Id
    @GeneratedValue
    private int id;
    private BigDecimal amount;
    private LocalDateTime timestampCreated;
    private boolean processed;
    private boolean win;
    @OneToOne(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    private OutcomeOdd outcomeOdd;
    @OneToOne(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    private Player player;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    
    
    public Wager() {
        super();
    }

    public Wager(BigDecimal amount, OutcomeOdd odd, Player player) {
        this.amount = amount;
        this.timestampCreated = LocalDateTime.now();
        this.processed = true;
        this.win = false;
        this.outcomeOdd = odd;
        this.player = player;
        this.currency = player.getCurrency();
    }
    public int getOutcomeOddId() {
        return outcomeOdd.getId();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getTimestampCreated() {
        return timestampCreated;
    }

    public void setTimestampCreated(LocalDateTime timestampCreated) {
        this.timestampCreated = timestampCreated;
    }

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public OutcomeOdd getOutcomeOdd() {
        return outcomeOdd;
    }

    public void setOdd(OutcomeOdd odd) {
        this.outcomeOdd = odd;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
    
    public String getSportEventTitle() {
        return outcomeOdd.getSportEventTitle();
    }

    public String getOutcomeDescription() {
        // TODO Auto-generated method stub
        return outcomeOdd.getOutcomeDescription();
    }

    public String getBetDescription() {
        return outcomeOdd.getBetDescription();
    }

    public BigDecimal getDecimalOutcomeOdd() {
        return outcomeOdd.getValue();
    }

    public Outcome getOutcome() {
        return outcomeOdd.getOutcome();
        
    }

    public SportEvent getSportEvent() {
        return outcomeOdd.getSportEvent();
    }

    public void increasePlayerBalanace(BigDecimal val) {
        player.increasePlayerBalanace(val);
        
    }

    public int getId() {
        return id;
    }

    public void setOutcomeOdd(OutcomeOdd outcomeOdd) {
        this.outcomeOdd = outcomeOdd;
    }
    
}
