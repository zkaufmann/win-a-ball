package com.promotion.winaball.service;

import com.promotion.winaball.service.dto.RedemptionAttempt;
import com.promotion.winaball.service.evaluation.WinnerEvaluationService;
import com.promotion.winaball.service.validation.RedemptionValidationResult;
import com.promotion.winaball.service.dto.RedemptionResult;
import com.promotion.winaball.service.validation.RedemptionValidator;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

public class CouponRedemptionService {

    private final RedemptionValidator redemptionValidator;
    private final WinnerEvaluationService winnerEvaluationService;
    private final RedemptionHistoryService redemptionHistoryService;

    public CouponRedemptionService(final RedemptionValidator redemptionValidator,
                                   final WinnerEvaluationService winnerEvaluationService,
                                   final RedemptionHistoryService redemptionHistoryService) {
        Objects.nonNull(redemptionValidator);
        Objects.nonNull(winnerEvaluationService);
        Objects.nonNull(redemptionHistoryService);

        this.redemptionValidator = redemptionValidator;
        this.winnerEvaluationService = winnerEvaluationService;
        this.redemptionHistoryService = redemptionHistoryService;
    }

    @Transactional
    public RedemptionResult redeemCoupon(final RedemptionAttempt redemptionAttempt) {
        final RedemptionValidationResult validationResult = redemptionValidator.validate(redemptionAttempt);

        if (validationResult.isValid()) {
            final boolean isWinner = winnerEvaluationService.evaluate(redemptionAttempt);
            redemptionHistoryService.saveRedemption(redemptionAttempt, isWinner);

            return new RedemptionResult(isWinner);
        } else {
            return new RedemptionResult(validationResult.getErrors());
        }
    }
}
