package com.promotion.winaball;

import com.promotion.winaball.service.CouponRedemptionService;
import com.promotion.winaball.service.dto.Customer;
import com.promotion.winaball.service.dto.RedemptionResult;
import com.promotion.winaball.service.dto.RedemptionAttempt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

public class RedemptionAttemptRunnable implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedemptionAttemptRunnable.class);

    private static final int NUM_ATTEMPTS = 10;
    private static final String TERRITORY_LUCKY_LAND = "LuckyLand";

    private static AtomicInteger nextCouponCode = new AtomicInteger(1_000_000_000);

    private final Customer customer;
    private final CouponRedemptionService couponRedemptionService;

    public RedemptionAttemptRunnable(final Customer customer,
                                     final CouponRedemptionService couponRedemptionService) {
        this.customer = customer;
        this.couponRedemptionService = couponRedemptionService;
    }

    @Override
    public void run() {
        final RedemptionAttempt.RedemptionAttemptBuilder redemptionAttemptBuilder =
                RedemptionAttempt.builder()
                .setTerritory(TERRITORY_LUCKY_LAND)
                .setCustomer(customer);

        for (int i = 0; i < NUM_ATTEMPTS; i++) {
            final RedemptionAttempt redemptionAttempt =
                    redemptionAttemptBuilder
                    .setCouponCode(Integer.toString(nextCouponCode.incrementAndGet()))
                    .build();

            LOGGER.info("REDEEEM - {} - {}",
                    redemptionAttempt.getCustomer().getName(), redemptionAttempt.getCouponCode());

            final RedemptionResult redemptionResult = couponRedemptionService.redeemCoupon(redemptionAttempt);

            LOGGER.info("REDEEEM - {} - {} - {}",
                    redemptionAttempt.getCustomer().getName(), redemptionAttempt.getCouponCode(), redemptionResult);
        }
    }
}
