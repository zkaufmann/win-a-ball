package com.promotion.winaball.service;

import com.promotion.winaball.persistence.entity.CustomerEntity;
import com.promotion.winaball.persistence.entity.RedemptionHistoryEntity;
import com.promotion.winaball.persistence.mapper.RedemptionHistoryMapper;
import com.promotion.winaball.service.dto.RedemptionAttempt;

import java.util.Objects;

public class RedemptionHistoryService {

    private final RedemptionHistoryMapper redemptionHistoryMapper;
    private final CustomerService customerService;

    public RedemptionHistoryService(final RedemptionHistoryMapper redemptionHistoryMapper,
                                    final CustomerService customerService) {
        Objects.nonNull(redemptionHistoryMapper);
        Objects.nonNull(customerService);

        this.redemptionHistoryMapper = redemptionHistoryMapper;
        this.customerService = customerService;
    }

    public void saveRedemption(final RedemptionAttempt redemptionAttempt, final boolean isWinner) {
        final CustomerEntity customerEntity = customerService.getCustomerEntity(redemptionAttempt.getCustomer());
        final RedemptionHistoryEntity historyEntity = new RedemptionHistoryEntity();
        historyEntity.setCustomerId(customerEntity.getId());
        historyEntity.setCouponCode(redemptionAttempt.getCouponCode());
        historyEntity.setTerritory(redemptionAttempt.getTerritory());
        historyEntity.setWinner(isWinner);
        redemptionHistoryMapper.insertRedeemedCode(historyEntity);
    }

}
