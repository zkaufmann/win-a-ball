package com.promotion.winaball.context;

import com.promotion.winaball.persistence.mapper.CustomerMapper;
import com.promotion.winaball.persistence.mapper.RedemptionHistoryMapper;
import com.promotion.winaball.persistence.mapper.TerritoryMapper;
import com.promotion.winaball.persistence.mapper.TerritoryStatusMapper;
import com.promotion.winaball.service.*;
import com.promotion.winaball.service.evaluation.DefaultWinnerEvaluationStrategy;
import com.promotion.winaball.service.evaluation.WinnerEvaluationService;
import com.promotion.winaball.service.evaluation.WinnerEvaluationStrategy;
import com.promotion.winaball.service.validation.RedemptionValidationStep;
import com.promotion.winaball.service.validation.RedemptionValidator;
import com.promotion.winaball.utils.TimeService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class RedemptionServiceDi {

    @Bean
    public CouponRedemptionService couponRedemptionService(final RedemptionValidator redemptionValidator,
                                                           final WinnerEvaluationService winnerEvaluationService,
                                                           final RedemptionHistoryService redemptionHistoryService) {
        return new CouponRedemptionService(redemptionValidator, winnerEvaluationService, redemptionHistoryService);
    }

    @Bean
    public RedemptionValidator redemptionValidator(final List<RedemptionValidationStep> validationSteps) {
        return new RedemptionValidator(validationSteps);
    }

    @Bean
    public WinnerEvaluationService winnerEvaluationService(final TerritoryMapper territoryMapper,
                                                           final TerritoryStatusMapper territoryStatusMapper,
                                                           final WinnerEvaluationStrategy winnerEvaluationStrategy,
                                                           final TimeService timeService) {
        return new WinnerEvaluationService(territoryMapper, territoryStatusMapper, winnerEvaluationStrategy, timeService);
    }

    @Bean
    public WinnerEvaluationStrategy winnerEvaluationStrategy() {
        return new DefaultWinnerEvaluationStrategy();
    }

    @Bean
    public RedemptionHistoryService redemptionHistoryService(final RedemptionHistoryMapper redemptionHistoryMapper,
                                                             final CustomerService customerService) {
        return new RedemptionHistoryService(redemptionHistoryMapper, customerService);
    }

    @Bean
    public CustomerService customerService(final CustomerMapper customerMapper) {
        return new CustomerService(customerMapper);
    }
}
