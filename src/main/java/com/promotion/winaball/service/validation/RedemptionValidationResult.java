package com.promotion.winaball.service.validation;

import com.google.common.base.MoreObjects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class RedemptionValidationResult {
    private final boolean isValid;
    private final List<RedemptionValidationStepResult> errors;

    public static RedemptionValidationResult createValid() {
        return new RedemptionValidationResult(true, Collections.emptyList());
    }

    public static RedemptionValidationResult createError(final List<RedemptionValidationStepResult> errors) {
        return new RedemptionValidationResult(false, errors);
    }

    private RedemptionValidationResult(final boolean isValid, final List<RedemptionValidationStepResult> errors) {
        Objects.nonNull(errors);

        this.isValid = isValid;
        this.errors = Collections.unmodifiableList(new ArrayList<>(errors));
    }

    public boolean isValid() {
        return isValid;
    }

    public List<RedemptionValidationStepResult> getErrors() {
        return errors;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final RedemptionValidationResult that = (RedemptionValidationResult) o;
        return isValid == that.isValid &&
                Objects.equals(errors, that.errors);
    }

    @Override
    public int hashCode() {

        return Objects.hash(isValid, errors);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("isValid", isValid)
                .add("errors", errors)
                .toString();
    }
}
