package com.epam.training.sportsbetting.domain;

import java.util.List;

public class Result {
    List<Outcome> winnerOutcomes;

    public Result(List<Outcome> winnerOutcomes) {
        super();
        this.winnerOutcomes = winnerOutcomes;
    }

    public List<Outcome> getWinnerOutcomes() {
        return winnerOutcomes;
    }
 
}
