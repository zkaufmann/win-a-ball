package com.promotion.winaball.service.evaluation;

import com.promotion.winaball.persistence.entity.TerritoryEntity;
import com.promotion.winaball.persistence.entity.TerritoryStatusEntity;
import com.promotion.winaball.service.evaluation.DefaultWinnerEvaluationStrategy;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DefaultWinnerEvaluationStrategyTest {
    private static final boolean WINNER = true;
    private static final boolean NOT_WINNER = false;

    private DefaultWinnerEvaluationStrategy testedObject;

    @Before
    public void setUp() {
        testedObject = new DefaultWinnerEvaluationStrategy();
    }

    @Test
    public void testWinner() {
        test(WINNER, 4, 5, 1, 10, 1, 100);
        test(WINNER, 4, 5, 9, 10, 99, 100);
        test(NOT_WINNER, 3, 5, 1, 10, 1, 100);
        test(NOT_WINNER, 4, 5, 1, 10, 100, 100);
        test(NOT_WINNER, 4, 5, 10, 10, 1, 100);
    }

    private void test(final boolean expected,
                      final int redeemsAfterWin,
                      final int winPeriod,
                      final int winsToday,
                      final int maxBallsPerDay,
                      final int winsTotal,
                      final int maxBalls) {
        // GIVEN
        final TerritoryEntity territoryEntity = new TerritoryEntity();
        territoryEntity.setWinPeriod(winPeriod);
        territoryEntity.setMaxBalls(maxBalls);
        territoryEntity.setMaxBallsPerDay(maxBallsPerDay);
        final TerritoryStatusEntity territoryStatusEntity = new TerritoryStatusEntity();
        territoryStatusEntity.setRedeemsAfterWin(redeemsAfterWin);
        territoryStatusEntity.setWinsTotal(winsTotal);
        territoryStatusEntity.setWinsToday(winsToday);

        // WHEN
        final boolean actual = testedObject.isWinner(territoryEntity, territoryStatusEntity);

        // THEN
        Assert.assertEquals(expected, actual);
    }
}