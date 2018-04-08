package com.promotion.winaball.persistence.mapper;

import com.promotion.winaball.persistence.entity.CustomerEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface CustomerMapper {

    @Select("select id from customer where email_address=#{emailAddress}")
    Long getCustomerId(@Param("emailAddress") String emailAddress);

    @Select("select * from customer where email_address=#{emailAddress}")
    CustomerEntity getCustomer(@Param("emailAddress") String emailAddress);

    @Insert("insert into customer (id, name, email_address, date_of_birth)"
            + " values(#{id}, #{name}, #{emailAddress}, #{dateOfBirth})")
    void insertCustomer(CustomerEntity customerEntity);
}
