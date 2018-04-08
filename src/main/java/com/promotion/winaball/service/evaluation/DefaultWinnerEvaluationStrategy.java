package com.promotion.winaball.service.evaluation;

import com.promotion.winaball.persistence.entity.TerritoryEntity;
import com.promotion.winaball.persistence.entity.TerritoryStatusEntity;

import java.util.Objects;

public final class DefaultWinnerEvaluationStrategy implements WinnerEvaluationStrategy {

    public DefaultWinnerEvaluationStrategy() {
    }

    @Override
    public boolean isWinner(final TerritoryEntity territoryEntity, final TerritoryStatusEntity territoryStatusEntity) {
        Objects.nonNull(territoryEntity);
        Objects.nonNull(territoryStatusEntity);

        final boolean stillHasAnyBalls = territoryStatusEntity.getWinsTotal() < territoryEntity.getMaxBalls();
        final boolean stillHasBallsForToday = territoryStatusEntity.getWinsToday() < territoryEntity.getMaxBallsPerDay();
        final boolean lucky = territoryStatusEntity.getRedeemsAfterWin() + 1 == territoryEntity.getWinPeriod();

        return stillHasAnyBalls && stillHasBallsForToday && lucky;
    }
}
