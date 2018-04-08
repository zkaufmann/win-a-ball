package com.promotion.winaball.service.validation.step;

import com.promotion.winaball.service.dto.RedemptionAttempt;
import com.promotion.winaball.service.validation.RedemptionValidationStep;
import com.promotion.winaball.service.validation.RedemptionValidationStepResult;

public class CouponCodeLengthValidationStep implements RedemptionValidationStep {

    private static final int COUPON_CODE_LENGTH = 10;

    public CouponCodeLengthValidationStep() {
    }

    @Override
    public RedemptionValidationStepResult validate(final RedemptionAttempt redemptionAttempt) {
        return redemptionAttempt.getCouponCode().length() == COUPON_CODE_LENGTH
                ? RedemptionValidationStepResult.VALID
                : RedemptionValidationStepResult.INVALID_COUPON_CODE;
    }
}
