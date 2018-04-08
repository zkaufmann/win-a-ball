package com.promotion.winaball.persistence;

import com.promotion.winaball.testutils.TestDatabaseInitializer;
import com.promotion.winaball.persistence.entity.CustomerEntity;
import com.promotion.winaball.persistence.mapper.CustomerMapper;
import com.promotion.winaball.persistence.mapper.RedemptionHistoryMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerPersistenceTest {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private RedemptionHistoryMapper redemptionHistoryMapper;
    @Autowired
    private CustomerMapper customerMapper;

    @Before
    public void setup() throws IOException, SQLException {
        new TestDatabaseInitializer().initialize(dataSource);
    }

    @Test
    public void testCustomerPersistence() {
        final long customerId = 1;
        final String emailAddress = "emailAddress";

        // first there are no customers
        final CustomerEntity customerEntityNone = customerMapper.getCustomer(emailAddress);
        Assert.assertNull(customerEntityNone);

        // insert a customer record
        final CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setName("name");
        customerEntity.setEmailAddress(emailAddress);
        customerEntity.setDateOfBirth(LocalDate.of(2000,1,1));
        customerMapper.insertCustomer(customerEntity);

        // record receives id 1
        final Long customerIdFromDb = customerMapper.getCustomerId(emailAddress);
        Assert.assertEquals(1L, customerIdFromDb.longValue());

        customerEntity.setId(customerIdFromDb);
        final CustomerEntity customerEntityFromDb = customerMapper.getCustomer(emailAddress);
        Assert.assertEquals(customerEntity, customerEntityFromDb);

    }
}
