package com.epam.training.sportsbetting.utility;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.epam.training.sportsbetting.domain.Bet;
import com.epam.training.sportsbetting.domain.BetBuilder;
import com.epam.training.sportsbetting.domain.BetType;
import com.epam.training.sportsbetting.domain.Outcome;
import com.epam.training.sportsbetting.domain.OutcomeOddBuilder;
import com.epam.training.sportsbetting.domain.SportEvent;
import com.epam.training.sportsbetting.domain.SportEventBuilder;

public class Utility {
    /*
    private List<Bet> bets;
    private SportEvent sportEvent;
    
    public SportEventBuilder setBets() {
        BetBuilder betBuilder = new BetBuilder();
        
        final LocalDateTime validFrom = LocalDateTime.parse("2000-01-01T12:00:00");
        final LocalDateTime validUntil = LocalDateTime.parse("2020-02-07T12:00:00");
        OutcomeOddBuilder outcomeOddBuilder = new OutcomeOddBuilder();
        outcomeOddBuilder.setValue(BigDecimal.valueOf(4)).setValidForm(validFrom).setValidUntil(validUntil);
        
        
        Bet bet = betBuilder.setDescription("player Lebron James score")
                .setSportevent(this.getInstance())
                .setType(BetType.PLAYERS_SCORE)
                .setOutcomeOdd(outcomeOddBuilder.getInstance(),0)
                .getInstance();
        bets.add(bet);
        bets.get(0).getOutcomes().add(new Outcome("28", bets.get(0)));
        
        
        betBuilder = new BetBuilder();
        bet = betBuilder.setDescription("number of scored goals")
                .setSportevent(this.getInstance())
                .setType(BetType.GOALS)
                .setOutcomeOdd(outcomeOddBuilder.setValue(BigDecimal.valueOf(3)).getInstance(),0)
                .getInstance();
        bets.add(bet);
        bets.get(1).getOutcomes().add(new Outcome("28", bets.get(1)));
        
        betBuilder = new BetBuilder();
        bet = betBuilder.setDescription("winner")
                .setSportevent(this.getInstance())
                .setType(BetType.WINNER)
                .addOutcome(new Outcome("Lakers", betBuilder.getInstance()))
                .setOutcomeOdd(outcomeOddBuilder.setValue(BigDecimal.valueOf(2)).getInstance(),0)
                .getInstance();
        bets.add(bet);
        bets.get(2).getOutcomes().add(new Outcome("Lakers", bets.get(2)));
        
        betBuilder = new BetBuilder();
        bet = betBuilder.setDescription("winner")
                .setSportevent(this.getInstance())
                .setType(BetType.WINNER)
                .setOutcomeOdd(outcomeOddBuilder.setValue(BigDecimal.valueOf(2)).getInstance(),0)
                .getInstance();
        bets.add(bet);
        bets.get(3).getOutcomes().add(new Outcome("Celtics", bets.get(3)));
        return this;
    }
    
    public SportEvent getInstance() {
        if(sportEvent != null) {
            return sportEvent;
        }
        sportEvent = new SportEvent(title, startDate, endDate, bets, result, player1, player2);
        return sportEvent;
    }
    */
}
