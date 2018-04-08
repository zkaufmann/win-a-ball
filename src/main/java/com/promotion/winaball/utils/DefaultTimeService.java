package com.promotion.winaball.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DefaultTimeService implements TimeService {
    @Override
    public LocalDate getCurrentDate() {
        return LocalDate.now();
    }

    @Override
    public LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now();
    }
}
