package com.promotion.winaball.service.validation.step;

import com.promotion.winaball.service.dto.Customer;
import com.promotion.winaball.service.dto.RedemptionAttempt;
import com.promotion.winaball.service.validation.RedemptionValidationStepResult;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class EmailAddressValidationStepTest {

    private EmailAddressValidationStep testedObject;

    @Before
    public void setUp() {
        testedObject = new EmailAddressValidationStep();
    }

    @Test
    public void testWhenEmailValidThenOk() {
        // GIVEN
        final Customer customer = Customer.builder()
                .setName("Alf A. Romeo")
                .setEmailAddress("qwe@asd.ca")
                .setDateOfBirth(LocalDate.of(2005,1,1))
                .build();
        final RedemptionAttempt redemptionAttempt = RedemptionAttempt.builder()
                .setCouponCode("0123456789")
                .setTerritory("territory")
                .setCustomer(customer)
                .build();
        final RedemptionValidationStepResult expected = RedemptionValidationStepResult.VALID;

        // WHEN
        final RedemptionValidationStepResult actual = testedObject.validate(redemptionAttempt);

        // THEN
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testWhenEmailInvalidThenError() {
        // GIVEN
        final Customer customer = Customer.builder()
                .setName("Alf A. Romeo")
                .setEmailAddress("qwe.ca")
                .setDateOfBirth(LocalDate.of(2005,1,1))
                .build();
        final RedemptionAttempt redemptionAttempt = RedemptionAttempt.builder()
                .setCouponCode("0123456789")
                .setTerritory("territory")
                .setCustomer(customer)
                .build();
        final RedemptionValidationStepResult expected = RedemptionValidationStepResult.INVALID_EMAIL_ADDRESS;

        // WHEN
        final RedemptionValidationStepResult actual = testedObject.validate(redemptionAttempt);

        // THEN
        Assert.assertEquals(expected, actual);
    }
}