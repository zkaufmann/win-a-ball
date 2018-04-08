package com.promotion.winaball.service.validation;

import com.promotion.winaball.service.dto.RedemptionAttempt;

public interface RedemptionValidationStep {
    RedemptionValidationStepResult validate(RedemptionAttempt redemptionAttempt);
}
