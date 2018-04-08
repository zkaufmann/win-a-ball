package com.promotion.winaball.context;

import com.promotion.winaball.persistence.mapper.RedemptionHistoryMapper;
import com.promotion.winaball.service.validation.step.AgeLimitValidationStep;
import com.promotion.winaball.service.validation.step.CouponCodeLengthValidationStep;
import com.promotion.winaball.service.validation.step.EmailAddressValidationStep;
import com.promotion.winaball.service.validation.step.MultipleRedemptionValidationStep;
import com.promotion.winaball.utils.TimeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidationStepsDi {
    @Bean
    public AgeLimitValidationStep ageLimitValidationStep(final TimeService timeService) {
        return new AgeLimitValidationStep(timeService);
    }

    @Bean
    public CouponCodeLengthValidationStep couponCodeLengthValidationStep() {
        return new CouponCodeLengthValidationStep();
    }

    @Bean
    public EmailAddressValidationStep emailAddressValidationStep() {
        return new EmailAddressValidationStep();
    }

    @Bean
    public MultipleRedemptionValidationStep multipleRedemptionValidationStep(final RedemptionHistoryMapper redemptionHistoryMapper) {
        return new MultipleRedemptionValidationStep(redemptionHistoryMapper);
    }
}
