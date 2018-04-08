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

public class CouponCodeLengthValidationStepTest {
    private CouponCodeLengthValidationStep testedObject;

    @Before
    public void setUp() {
        testedObject = new CouponCodeLengthValidationStep();
    }

    @Test
    public void testWhenCodeLengthIs10ThenValid() {
        // GIVEN
        final String couponCode = "0123456789";
        final Customer customer = Customer.builder()
                .setName("Alf A. Romeo")
                .setEmailAddress("qwe@asd.ca")
                .setDateOfBirth(LocalDate.of(2005,1,1))
                .build();
        final RedemptionAttempt redemptionAttempt = RedemptionAttempt.builder()
                .setCouponCode(couponCode)
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
    public void testWhenCodeLengthIs9ThenInvalid() {
        // GIVEN
        final String couponCode = "012345678";
        final Customer customer = Customer.builder()
                .setName("Alf A. Romeo")
                .setEmailAddress("qwe@asd.ca")
                .setDateOfBirth(LocalDate.of(2005,1,1))
                .build();
        final RedemptionAttempt redemptionAttempt = RedemptionAttempt.builder()
                .setCouponCode(couponCode)
                .setTerritory("territory")
                .setCustomer(customer)
                .build();
        final RedemptionValidationStepResult expected = RedemptionValidationStepResult.INVALID_COUPON_CODE;

        // WHEN
        final RedemptionValidationStepResult actual = testedObject.validate(redemptionAttempt);

        // THEN
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testWhenCodeLengthIs11ThenInvalid() {
        // GIVEN
        final String couponCode = "01234567890";
        final Customer customer = Customer.builder()
                .setName("Alf A. Romeo")
                .setEmailAddress("qwe@asd.ca")
                .setDateOfBirth(LocalDate.of(2005,1,1))
                .build();
        final RedemptionAttempt redemptionAttempt = RedemptionAttempt.builder()
                .setCouponCode(couponCode)
                .setTerritory("territory")
                .setCustomer(customer)
                .build();
        final RedemptionValidationStepResult expected = RedemptionValidationStepResult.INVALID_COUPON_CODE;

        // WHEN
        final RedemptionValidationStepResult actual = testedObject.validate(redemptionAttempt);

        // THEN
        Assert.assertEquals(expected, actual);
    }
}