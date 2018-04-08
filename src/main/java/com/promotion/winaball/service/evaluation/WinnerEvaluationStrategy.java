package com.promotion.winaball.service.evaluation;

import com.promotion.winaball.persistence.entity.TerritoryEntity;
import com.promotion.winaball.persistence.entity.TerritoryStatusEntity;

public interface WinnerEvaluationStrategy {
    boolean isWinner(TerritoryEntity territoryEntity, TerritoryStatusEntity territoryStatusEntity);
}
