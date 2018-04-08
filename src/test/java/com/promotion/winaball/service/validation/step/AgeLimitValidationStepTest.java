package com.promotion.winaball.service.validation.step;

import com.promotion.winaball.service.dto.Customer;
import com.promotion.winaball.service.dto.RedemptionAttempt;
import com.promotion.winaball.service.validation.RedemptionValidationStepResult;
import com.promotion.winaball.utils.TimeService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;

@RunWith(MockitoJUnitRunner.class)
public class AgeLimitValidationStepTest {
    private AgeLimitValidationStep testedObject;

    @Mock
    private TimeService timeServiceMock;

    @Before
    public void setUp() {
        testedObject = new AgeLimitValidationStep(timeServiceMock);
    }

    @Test
    public void testWhenAgeIs13ThenOk() {
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

        Mockito.when(timeServiceMock.getCurrentDate()).thenReturn(LocalDate.of(2018,1,1));

        // WHEN
        final RedemptionValidationStepResult actual = testedObject.validate(redemptionAttempt);

        // THEN
        Mockito.verify(timeServiceMock).getCurrentDate();
        Mockito.verifyNoMoreInteractions(timeServiceMock);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testWhenAgeIs12ThenViolation() {
        // GIVEN
        final Customer customer = Customer.builder()
                .setName("Alf A. Romeo")
                .setEmailAddress("qwe@asd.ca")
                .setDateOfBirth(LocalDate.of(2006,1,1))
                .build();
        final RedemptionAttempt redemptionAttempt = RedemptionAttempt.builder()
                .setCouponCode("0123456789")
                .setTerritory("territory")
                .setCustomer(customer)
                .build();
        final RedemptionValidationStepResult expected = RedemptionValidationStepResult.AGE_LIMIT_VIOLATED;

        Mockito.when(timeServiceMock.getCurrentDate()).thenReturn(LocalDate.of(2018,1,1));

        // WHEN
        final RedemptionValidationStepResult actual = testedObject.validate(redemptionAttempt);

        // THEN
        Mockito.verify(timeServiceMock).getCurrentDate();
        Mockito.verifyNoMoreInteractions(timeServiceMock);
        Assert.assertEquals(expected, actual);
    }
}