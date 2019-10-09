package com.epam.training.sportsbetting.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.epam.training.sportsbetting.domain.Outcome;
import com.epam.training.sportsbetting.domain.OutcomeOdd;

public class OutcomeOddBuilder {
    private BigDecimal value;
    private LocalDateTime validForm;
    private LocalDateTime validUntil;
    private Outcome outcome;
    



    public OutcomeOddBuilder setValue(BigDecimal value) {
        this.value = value;
        return this;
    }



    public OutcomeOddBuilder setValidForm(LocalDateTime validForm) {
        this.validForm = validForm;
        return this;
    }




    public OutcomeOddBuilder setValidUntil(LocalDateTime validUnit) {
        this.validUntil = validUnit;
        return this;
    }



    public OutcomeOddBuilder setOutcome(Outcome outcome) {
        this.outcome = outcome;
        return this;
    }
    
    public OutcomeOdd getInstance() {
        return new OutcomeOdd(value, validForm, validUntil, outcome);
    }
    
    
}
