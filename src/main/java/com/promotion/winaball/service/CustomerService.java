package com.promotion.winaball.service;

import com.promotion.winaball.persistence.entity.CustomerEntity;
import com.promotion.winaball.persistence.mapper.CustomerMapper;
import com.promotion.winaball.service.dto.Customer;

import java.util.Objects;

public class CustomerService {
    private final CustomerMapper customerMapper;
    private final Object customerLock = new Object();

    public CustomerService(final CustomerMapper customerMapper) {
        Objects.nonNull(customerMapper);

        this.customerMapper = customerMapper;
    }

    public CustomerEntity getCustomerEntity(final Customer customer) {
        synchronized (customerLock) {
            CustomerEntity customerEntity = customerMapper.getCustomer(customer.getEmailAddress());
            if (customerEntity == null) {
                customerEntity = new CustomerEntity();
                customerEntity.setName(customer.getName());
                customerEntity.setEmailAddress(customer.getEmailAddress());
                customerEntity.setDateOfBirth(customer.getDateOfBirth());
                customerMapper.insertCustomer(customerEntity);
                customerEntity.setId(customerMapper.getCustomerId(customer.getEmailAddress()));
            }
            return customerEntity;
        }
    }
}
