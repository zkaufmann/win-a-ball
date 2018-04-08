package com.promotion.winaball.persistence.entity;

import com.google.common.base.MoreObjects;

import java.util.Objects;

public class TerritoryEntity {
    private Long id;
    private String name;
    private Integer winPeriod;
    private Integer maxBalls;
    private Integer maxBallsPerDay;

    public TerritoryEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Integer getWinPeriod() {
        return winPeriod;
    }

    public void setWinPeriod(final Integer winPeriod) {
        this.winPeriod = winPeriod;
    }

    public Integer getMaxBalls() {
        return maxBalls;
    }

    public void setMaxBalls(final Integer maxBalls) {
        this.maxBalls = maxBalls;
    }

    public Integer getMaxBallsPerDay() {
        return maxBallsPerDay;
    }

    public void setMaxBallsPerDay(final Integer maxBallsPerDay) {
        this.maxBallsPerDay = maxBallsPerDay;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final TerritoryEntity territoryEntity = (TerritoryEntity) o;
        return Objects.equals(id, territoryEntity.id) &&
                Objects.equals(name, territoryEntity.name) &&
                Objects.equals(winPeriod, territoryEntity.winPeriod) &&
                Objects.equals(maxBalls, territoryEntity.maxBalls) &&
                Objects.equals(maxBallsPerDay, territoryEntity.maxBallsPerDay);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, winPeriod, maxBalls, maxBallsPerDay);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("winPeriod", winPeriod)
                .add("maxBalls", maxBalls)
                .add("maxBallsPerDay", maxBallsPerDay)
                .toString();
    }
}
