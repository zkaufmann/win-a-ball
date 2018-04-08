package com.promotion.winaball.service.validation;

import com.promotion.winaball.service.dto.RedemptionAttempt;
import com.promotion.winaball.service.validation.*;
import com.promotion.winaball.service.validation.step.CouponCodeLengthValidationStep;
import com.promotion.winaball.service.validation.step.EmailAddressValidationStep;
import com.promotion.winaball.service.validation.step.MultipleRedemptionValidationStep;

import java.util.*;
import java.util.stream.Collectors;

public class RedemptionValidator {

    // static list defining the order of validation steps
    private static final List<Class<? extends RedemptionValidationStep>> stepOrder = Arrays.asList(
            EmailAddressValidationStep.class,
            CouponCodeLengthValidationStep.class,
            MultipleRedemptionValidationStep.class
            );

    // actual validation steps injected from the application context
    private final List<RedemptionValidationStep> validationSteps;

    public RedemptionValidator(final List<RedemptionValidationStep> validationSteps) {
        Objects.nonNull(validationSteps);

        this.validationSteps = Collections.unmodifiableList(new ArrayList<>(validationSteps));
    }

    public RedemptionValidationResult validate(final RedemptionAttempt redemptionAttempt) {
        Objects.nonNull(redemptionAttempt);

        final Map<Class<? extends RedemptionValidationStep>, RedemptionValidationStep> stepMap =
                validationSteps.stream().collect(Collectors.toMap(step -> step.getClass(), step -> step));

        final List<RedemptionValidationStepResult> errorList = new LinkedList<>();
        for (final Class<? extends RedemptionValidationStep> stepClass : stepOrder) {
            final RedemptionValidationStepResult result = stepMap.get(stepClass).validate(redemptionAttempt);
            if (result != RedemptionValidationStepResult.VALID) {
                errorList.add(result);
            }
        }
        if (errorList.isEmpty()) {
            return RedemptionValidationResult.createValid();
        } else {
            return RedemptionValidationResult.createError(errorList);
        }

        //  CouponCode
        //      length
        //      redeemed before

        //  Customer
        //      email
        //      birthday

    }
}
