package com.promotion.winaball.service.dto;

import com.google.common.base.MoreObjects;
import com.promotion.winaball.service.validation.RedemptionValidationStepResult;

import java.util.*;

public class RedemptionResult {
    private final List<RedemptionValidationStepResult> validationErrors;
    private final Optional<Boolean> winner;

    public RedemptionResult(final List<RedemptionValidationStepResult> validationErrors) {
        Objects.nonNull(validationErrors);

        this.validationErrors = Collections.unmodifiableList(new LinkedList<>(validationErrors));
        this.winner = Optional.empty();
    }

    public RedemptionResult(final Boolean winner) {
        Objects.nonNull(winner);

        this.validationErrors = Collections.emptyList();
        this.winner = Optional.of(winner);
    }

    public List<RedemptionValidationStepResult> getValidationErrors() {
        return validationErrors;
    }

    public Optional<Boolean> getWinner() {
        return winner;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final RedemptionResult that = (RedemptionResult) o;
        return Objects.equals(validationErrors, that.validationErrors) &&
                Objects.equals(winner, that.winner);
    }

    @Override
    public int hashCode() {

        return Objects.hash(validationErrors, winner);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("validationErrors", validationErrors)
                .add("winner", winner)
                .toString();
    }
}
