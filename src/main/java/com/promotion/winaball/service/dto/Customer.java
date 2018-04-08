package com.promotion.winaball.service.dto;

import com.google.common.base.MoreObjects;

import java.time.LocalDate;
import java.util.Objects;

public class Customer {
    private final String name;
    private final String emailAddress;
    private final LocalDate dateOfBirth;

    private Customer(final CustomerBuilder builder) {
        Objects.nonNull(builder.name);
        Objects.nonNull(builder.emailAddress);
        Objects.nonNull(builder.dateOfBirth);

        this.name = builder.name;
        this.emailAddress = builder.emailAddress;
        this.dateOfBirth = builder.dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public static CustomerBuilder builder() {
        return new CustomerBuilder();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Customer customer = (Customer) o;
        return Objects.equals(name, customer.name) &&
                Objects.equals(emailAddress, customer.emailAddress) &&
                Objects.equals(dateOfBirth, customer.dateOfBirth);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, emailAddress, dateOfBirth);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", name)
                .add("emailAddress", emailAddress)
                .add("dateOfBirth", dateOfBirth)
                .toString();
    }

    public static final class CustomerBuilder {
        private String name;
        private String emailAddress;
        private LocalDate dateOfBirth;

        private CustomerBuilder() {
        }

        public CustomerBuilder setName(final String name) {
            this.name = name;
            return this;
        }

        public CustomerBuilder setEmailAddress(final String emailAddress) {
            this.emailAddress = emailAddress;
            return this;
        }

        public CustomerBuilder setDateOfBirth(final LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }
}
