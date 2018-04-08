package com.promotion.winaball.service.validation.step;

import com.promotion.winaball.service.dto.RedemptionAttempt;
import com.promotion.winaball.service.validation.RedemptionValidationStep;
import com.promotion.winaball.service.validation.RedemptionValidationStepResult;
import com.promotion.winaball.utils.TimeService;

import java.time.LocalDate;
import java.util.Objects;

import static java.time.temporal.ChronoUnit.YEARS;

public class AgeLimitValidationStep implements RedemptionValidationStep {

    // TODO: in actual system this could come from configuration
    private static final int AGE_LOWER_LIMIT = 13;

    private final TimeService timeService;

    public AgeLimitValidationStep(final TimeService timeService) {
        Objects.nonNull(timeService);

        this.timeService = timeService;
    }

    @Override
    public RedemptionValidationStepResult validate(final RedemptionAttempt redemptionAttempt) {
        Objects.nonNull(redemptionAttempt);

        return YEARS.between(redemptionAttempt.getCustomer().getDateOfBirth(), timeService.getCurrentDate())
                >= AGE_LOWER_LIMIT
                ? RedemptionValidationStepResult.VALID
                : RedemptionValidationStepResult.AGE_LIMIT_VIOLATED;
    }
}
