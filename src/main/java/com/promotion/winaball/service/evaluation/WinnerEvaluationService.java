package com.promotion.winaball.service.evaluation;

import com.promotion.winaball.persistence.entity.TerritoryEntity;
import com.promotion.winaball.persistence.entity.TerritoryStatusEntity;
import com.promotion.winaball.persistence.mapper.TerritoryMapper;
import com.promotion.winaball.persistence.mapper.TerritoryStatusMapper;
import com.promotion.winaball.service.dto.RedemptionAttempt;
import com.promotion.winaball.utils.TimeService;

import java.util.Objects;

public class WinnerEvaluationService {

    private final TerritoryMapper territoryMapper;
    private final TerritoryStatusMapper territoryStatusMapper;
    private final WinnerEvaluationStrategy winnerEvaluationStrategy;
    private final TimeService timeService;

    private final Object evaluationLock = new Object();

    public WinnerEvaluationService(final TerritoryMapper territoryMapper,
                                   final TerritoryStatusMapper territoryStatusMapper,
                                   final WinnerEvaluationStrategy winnerEvaluationStrategy,
                                   final TimeService timeService) {
        Objects.nonNull(territoryMapper);
        Objects.nonNull(territoryStatusMapper);
        Objects.nonNull(winnerEvaluationStrategy);
        Objects.nonNull(timeService);

        this.territoryMapper = territoryMapper;
        this.territoryStatusMapper = territoryStatusMapper;
        this.winnerEvaluationStrategy = winnerEvaluationStrategy;
        this.timeService = timeService;
    }

    public boolean evaluate(final RedemptionAttempt redemptionAttempt) {
        Objects.nonNull(redemptionAttempt);

        synchronized (evaluationLock) {
            final TerritoryEntity territoryEntity = territoryMapper.getTerritory(redemptionAttempt.getTerritory());
            final TerritoryStatusEntity territoryStatusEntity = getTerritoryStatus(territoryEntity);
            final boolean isWinner = winnerEvaluationStrategy.isWinner(territoryEntity, territoryStatusEntity);
            updateTerritoryStatus(territoryStatusEntity, isWinner);

            return isWinner;
        }
    }

    private TerritoryStatusEntity getTerritoryStatus(final TerritoryEntity territoryEntity) {
        final TerritoryStatusEntity territoryStatusEntity = territoryStatusMapper.getStatus(territoryEntity.getId());
        if (!previousUpdateIsToday(territoryStatusEntity)) {
            territoryStatusEntity.setWinsToday(0);
        }
        return territoryStatusEntity;
    }

    private boolean previousUpdateIsToday(final TerritoryStatusEntity territoryStatusEntity) {
        return timeService.getCurrentDate().equals(territoryStatusEntity.getModifiedAt().toLocalDate());
    }

    private void updateTerritoryStatus(final TerritoryStatusEntity territoryStatusEntity, final boolean isWinner) {
        if (isWinner) {
            territoryStatusEntity.setWinsToday(territoryStatusEntity.getWinsToday() + 1);
            territoryStatusEntity.setWinsTotal(territoryStatusEntity.getWinsTotal() + 1);
            territoryStatusEntity.setRedeemsAfterWin(0);
        } else {
            territoryStatusEntity.setRedeemsAfterWin(territoryStatusEntity.getRedeemsAfterWin() + 1);
        }

        territoryStatusMapper.updateStatus(territoryStatusEntity);
    }
}
