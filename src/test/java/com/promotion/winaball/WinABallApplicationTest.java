package com.promotion.winaball;

import com.promotion.winaball.service.CouponRedemptionService;
import com.promotion.winaball.service.dto.Customer;
import com.promotion.winaball.service.dto.RedemptionResult;
import com.promotion.winaball.service.dto.RedemptionAttempt;
import com.promotion.winaball.service.validation.RedemptionValidationStepResult;
import com.promotion.winaball.testutils.TestDatabaseInitializer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;

import static org.apache.ibatis.io.Resources.getResourceAsReader;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WinABallApplicationTest {
    private static final String TERRITORY_GERMANY = "Germany";
    private static final String TERRITORY_LUCKY_LAND = "LuckyLand";

    @Autowired
    private DataSource dataSource;
    @Autowired
    private CouponRedemptionService couponRedemptionService;

    @Before
    public void setup() throws IOException, SQLException {
        new TestDatabaseInitializer().initialize(dataSource);
    }

    @Test
    public void contextLoads() {

    }

    @Test
    public void redemptionTestNotWinner(){
        // GIVEN
        final Customer customer = Customer.builder()
                .setName("Alf A. Romeo")
                .setEmailAddress("qwe@asd.ca")
                .setDateOfBirth(LocalDate.of(2000,1,1))
                .build();
        final RedemptionAttempt redemptionAttempt = RedemptionAttempt.builder()
                .setCouponCode("0123456789")
                .setTerritory(TERRITORY_GERMANY)
                .setCustomer(customer)
                .build();
        final RedemptionResult expected = new RedemptionResult(false);

        // WHEN
        final RedemptionResult actual = couponRedemptionService.redeemCoupon(redemptionAttempt);

        // THEN
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void redemptionTestWinner(){
        // GIVEN
        final Customer customer = Customer.builder()
                .setName("Alf A. Romeo")
                .setEmailAddress("qwe@asd.ca")
                .setDateOfBirth(LocalDate.of(2000,1,1))
                .build();
        final RedemptionAttempt redemptionAttempt1 = RedemptionAttempt.builder()
                .setCouponCode("0123456789")
                .setTerritory(TERRITORY_LUCKY_LAND)
                .setCustomer(customer)
                .build();
        final RedemptionAttempt redemptionAttempt2 = RedemptionAttempt.builder()
                .setCouponCode("1123456789")
                .setTerritory(TERRITORY_LUCKY_LAND)
                .setCustomer(customer)
                .build();
        final RedemptionResult expected1 = new RedemptionResult(false);
        final RedemptionResult expected2 = new RedemptionResult(true);

        // WHEN
        final RedemptionResult actual1 = couponRedemptionService.redeemCoupon(redemptionAttempt1);
        final RedemptionResult actual2 = couponRedemptionService.redeemCoupon(redemptionAttempt2);

        // THEN
        Assert.assertEquals(expected1, actual1);
        Assert.assertEquals(expected2, actual2);
    }

    @Test
    public void redemptionValidationFails(){
        // GIVEN
        final Customer customer = Customer.builder()
                .setName("Alf A. Romeo")
                .setEmailAddress("qwe@asd.ca")
                .setDateOfBirth(LocalDate.of(2000,1,1))
                .build();
        final RedemptionAttempt redemptionAttempt = RedemptionAttempt.builder()
                .setCouponCode("0123456789")
                .setTerritory(TERRITORY_GERMANY)
                .setCustomer(customer)
                .build();
        final RedemptionResult expected =
                new RedemptionResult(Arrays.asList(RedemptionValidationStepResult.COUPON_CODE_ALREADY_REDEEMED));

        // WHEN
        couponRedemptionService.redeemCoupon(redemptionAttempt);
        final RedemptionResult actual = couponRedemptionService.redeemCoupon(redemptionAttempt);

        // THEN
        Assert.assertEquals(expected, actual);
    }

}
