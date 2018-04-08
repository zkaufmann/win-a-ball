package com.promotion.winaball.service.dto;

import com.google.common.base.MoreObjects;

import java.util.Objects;

public class RedemptionAttempt {
    private final String couponCode;
    private final String territory;
    private final Customer customer;

    private RedemptionAttempt(final RedemptionAttemptBuilder builder) {
        Objects.nonNull(builder.couponCode);
        Objects.nonNull(builder.territory);
        Objects.nonNull(builder.customer);

        this.couponCode = builder.couponCode;
        this.territory = builder.territory;
        this.customer = builder.customer;
    }

    public static RedemptionAttemptBuilder builder() {
        return new RedemptionAttemptBuilder();
    }

    public String getCouponCode() {
        return couponCode;
    }

    public String getTerritory() {
        return territory;
    }

    public Customer getCustomer() {
        return customer;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final RedemptionAttempt that = (RedemptionAttempt) o;
        return Objects.equals(couponCode, that.couponCode) &&
                Objects.equals(territory, that.territory) &&
                Objects.equals(customer, that.customer);
    }

    @Override
    public int hashCode() {

        return Objects.hash(couponCode, territory, customer);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("couponCode", couponCode)
                .add("territory", territory)
                .add("customer", customer)
                .toString();
    }

    public static final class RedemptionAttemptBuilder {
        private String couponCode;
        private String territory;
        private Customer customer;

        private RedemptionAttemptBuilder() {
        }

        public RedemptionAttemptBuilder setCouponCode(final String couponCode) {
            this.couponCode = couponCode;
            return this;
        }

        public RedemptionAttemptBuilder setTerritory(final String territory) {
            this.territory = territory;
            return this;
        }

        public RedemptionAttemptBuilder setCustomer(final Customer customer) {
            this.customer = customer;
            return this;
        }

        public RedemptionAttempt build() {
            return new RedemptionAttempt(this);
        }
    }
}
