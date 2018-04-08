package com.promotion.winaball.context;

import com.promotion.winaball.utils.DefaultTimeService;
import com.promotion.winaball.utils.TimeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UtilsDi {
    @Bean
    public TimeService timeService() {
        return new DefaultTimeService();
    }
}
