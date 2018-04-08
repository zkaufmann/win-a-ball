package com.promotion.winaball.service.validation.step;

import com.promotion.winaball.service.dto.RedemptionAttempt;
import com.promotion.winaball.service.validation.RedemptionValidationStep;
import com.promotion.winaball.service.validation.RedemptionValidationStepResult;
import org.apache.commons.validator.routines.EmailValidator;

public class EmailAddressValidationStep implements RedemptionValidationStep {

    public EmailAddressValidationStep() {
    }

    @Override
    public RedemptionValidationStepResult validate(final RedemptionAttempt redemptionAttempt) {
        return EmailValidator.getInstance().isValid(redemptionAttempt.getCustomer().getEmailAddress())
                ? RedemptionValidationStepResult.VALID
                : RedemptionValidationStepResult.INVALID_EMAIL_ADDRESS;
    }
}
