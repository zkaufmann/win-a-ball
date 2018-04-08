package com.promotion.winaball.persistence.entity;

import com.google.common.base.MoreObjects;

import java.time.LocalDate;
import java.util.Objects;

public class CustomerEntity {
    private Long id;
    private String name;
    private String emailAddress;
    private LocalDate dateOfBirth;

    public CustomerEntity() {
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

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(final String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(final LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final CustomerEntity that = (CustomerEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(emailAddress, that.emailAddress) &&
                Objects.equals(dateOfBirth, that.dateOfBirth);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, emailAddress, dateOfBirth);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("emailAddress", emailAddress)
                .add("dateOfBirth", dateOfBirth)
                .toString();
    }
}
