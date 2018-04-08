package com.promotion.winaball.service.validation.step;

import com.promotion.winaball.persistence.mapper.RedemptionHistoryMapper;
import com.promotion.winaball.service.dto.RedemptionAttempt;
import com.promotion.winaball.service.validation.RedemptionValidationStep;
import com.promotion.winaball.service.validation.RedemptionValidationStepResult;

import java.util.Objects;

public class MultipleRedemptionValidationStep implements RedemptionValidationStep {
    private final RedemptionHistoryMapper redemptionHistoryMapper;

    public MultipleRedemptionValidationStep(final RedemptionHistoryMapper redemptionHistoryMapper) {
        Objects.nonNull(redemptionHistoryMapper);

        this.redemptionHistoryMapper = redemptionHistoryMapper;
    }

    @Override
    public RedemptionValidationStepResult validate(final RedemptionAttempt redemptionAttempt) {
        return redemptionHistoryMapper.isCodeRedeemed(redemptionAttempt.getCouponCode())
                ? RedemptionValidationStepResult.COUPON_CODE_ALREADY_REDEEMED
                : RedemptionValidationStepResult.VALID;
    }
}
