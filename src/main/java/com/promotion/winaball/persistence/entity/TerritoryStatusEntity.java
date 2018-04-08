package com.promotion.winaball.persistence.entity;

import com.google.common.base.MoreObjects;

import java.time.LocalDateTime;
import java.util.Objects;

public class TerritoryStatusEntity {
    private Long territoryId;
    private Integer redeemsAfterWin;
    private Integer winsToday;
    private Integer winsTotal;
    private LocalDateTime modifiedAt;

    public TerritoryStatusEntity() {
    }

    public Long getTerritoryId() {
        return territoryId;
    }

    public void setTerritoryId(final Long territoryId) {
        this.territoryId = territoryId;
    }

    public Integer getRedeemsAfterWin() {
        return redeemsAfterWin;
    }

    public void setRedeemsAfterWin(final Integer redeemsAfterWin) {
        this.redeemsAfterWin = redeemsAfterWin;
    }

    public Integer getWinsToday() {
        return winsToday;
    }

    public void setWinsToday(final Integer winsToday) {
        this.winsToday = winsToday;
    }

    public Integer getWinsTotal() {
        return winsTotal;
    }

    public void setWinsTotal(final Integer winsTotal) {
        this.winsTotal = winsTotal;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(final LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final TerritoryStatusEntity that = (TerritoryStatusEntity) o;
        return Objects.equals(territoryId, that.territoryId) &&
                Objects.equals(redeemsAfterWin, that.redeemsAfterWin) &&
                Objects.equals(winsToday, that.winsToday) &&
                Objects.equals(winsTotal, that.winsTotal) &&
                Objects.equals(modifiedAt, that.modifiedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(territoryId, redeemsAfterWin, winsToday, winsTotal, modifiedAt);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("territoryId", territoryId)
                .add("redeemsAfterWin", redeemsAfterWin)
                .add("winsToday", winsToday)
                .add("winsTotal", winsTotal)
                .add("modifiedAt", modifiedAt)
                .toString();
    }
}
