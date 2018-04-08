package com.promotion.winaball.persistence.entity;

import com.google.common.base.MoreObjects;

import java.util.Objects;

public class RedemptionHistoryEntity {
    private Long customerId;
    private String couponCode;
    private String territory;
    private Boolean isWinner;

    public RedemptionHistoryEntity() {
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(final Long customerId) {
        this.customerId = customerId;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(final String couponCode) {
        this.couponCode = couponCode;
    }

    public String getTerritory() {
        return territory;
    }

    public void setTerritory(final String territory) {
        this.territory = territory;
    }

    public Boolean getWinner() {
        return isWinner;
    }

    public void setWinner(final Boolean winner) {
        isWinner = winner;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final RedemptionHistoryEntity that = (RedemptionHistoryEntity) o;
        return Objects.equals(customerId, that.customerId) &&
                Objects.equals(couponCode, that.couponCode) &&
                Objects.equals(territory, that.territory) &&
                Objects.equals(isWinner, that.isWinner);
    }

    @Override
    public int hashCode() {

        return Objects.hash(customerId, couponCode, territory, isWinner);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customerId", customerId)
                .add("couponCode", couponCode)
                .add("territory", territory)
                .add("isWinner", isWinner)
                .toString();
    }
}
