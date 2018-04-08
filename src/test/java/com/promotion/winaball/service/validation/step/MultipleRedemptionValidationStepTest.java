package com.promotion.winaball.service.validation.step;

import com.promotion.winaball.persistence.mapper.RedemptionHistoryMapper;
import com.promotion.winaball.service.dto.Customer;
import com.promotion.winaball.service.dto.RedemptionAttempt;
import com.promotion.winaball.service.validation.RedemptionValidationStepResult;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class MultipleRedemptionValidationStepTest {
    private MultipleRedemptionValidationStep testedObject;

    @Mock
    private RedemptionHistoryMapper redemptionHistoryMapperMock;

    @Before
    public void setUp() {
        testedObject = new MultipleRedemptionValidationStep(redemptionHistoryMapperMock);
    }

    @Test
    public void testWhenCodeNotYetRedeemedThenValid() {
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

        Mockito.when(redemptionHistoryMapperMock.isCodeRedeemed(couponCode)).thenReturn(false);

        // WHEN
        final RedemptionValidationStepResult actual = testedObject.validate(redemptionAttempt);

        // THEN
        Mockito.verify(redemptionHistoryMapperMock).isCodeRedeemed(couponCode);
        Mockito.verifyNoMoreInteractions(redemptionHistoryMapperMock);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testWhenCodeAlreadyRedeemedThenInvalid() {
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
        final RedemptionValidationStepResult expected = RedemptionValidationStepResult.COUPON_CODE_ALREADY_REDEEMED;

        Mockito.when(redemptionHistoryMapperMock.isCodeRedeemed(couponCode)).thenReturn(true);

        // WHEN
        final RedemptionValidationStepResult actual = testedObject.validate(redemptionAttempt);

        // THEN
        Mockito.verify(redemptionHistoryMapperMock).isCodeRedeemed(couponCode);
        Mockito.verifyNoMoreInteractions(redemptionHistoryMapperMock);
        Assert.assertEquals(expected, actual);
    }
}