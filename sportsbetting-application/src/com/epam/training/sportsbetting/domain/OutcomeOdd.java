package com.epam.training.sportsbetting.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OutcomeOdd {
    private BigDecimal value;
    private LocalDateTime validForm;
    private LocalDateTime validUntil;
    private Outcome outcome;
    
    
    public OutcomeOdd(BigDecimal value, LocalDateTime validForm, LocalDateTime validUnit, Outcome outcome) {
        super();
        this.value = value;
        this.validForm = validForm;
        this.validUntil = validUnit;
        this.outcome = outcome;
    }


    public BigDecimal getValue() {
        return value;
    }


    public void setValue(BigDecimal value) {
        this.value = value;
    }


    public LocalDateTime getValidForm() {
        return validForm;
    }


    public void setValidForm(LocalDateTime validForm) {
        this.validForm = validForm;
    }


    public LocalDateTime getValidUntil() {
        return validUntil;
    }


    public void setValidUnit(LocalDateTime validUnit) {
        this.validUntil = validUnit;
    }


    public Outcome getOutcome() {
        return outcome;
    }


    public void setOutcome(Outcome outcome) {
        this.outcome = outcome;
    }
    
    
    public String getOutcomeDescription() {
        return outcome.getDescription();
    }
    
    public String getSportEventTitle() {
        return outcome.getSportEventTitle();
    }
    
}
