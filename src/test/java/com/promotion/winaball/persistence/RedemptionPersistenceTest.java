package com.promotion.winaball.persistence;

import com.promotion.winaball.testutils.TestDatabaseInitializer;
import com.promotion.winaball.persistence.entity.CustomerEntity;
import com.promotion.winaball.persistence.entity.RedemptionHistoryEntity;
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
public class RedemptionPersistenceTest {
    private static final String TERRITORY_GERMANY = "Germany";

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
    public void testRedemptionHistoryPersistence() {
        final long customerId = 1;
        final CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(customerId);
        customerEntity.setName("name");
        customerEntity.setEmailAddress("email");
        customerEntity.setDateOfBirth(LocalDate.of(2000,1,1));
        customerMapper.insertCustomer(customerEntity);

        final String code1 = "1";
        final RedemptionHistoryEntity historyEntity = new RedemptionHistoryEntity();
        historyEntity.setCustomerId(customerId);
        historyEntity.setCouponCode(code1);
        historyEntity.setTerritory(TERRITORY_GERMANY);
        historyEntity.setWinner(true);
        redemptionHistoryMapper.insertRedeemedCode(historyEntity);

        boolean redeemed0 = redemptionHistoryMapper.isCodeRedeemed(code1);
        final String code2 = "2";
        boolean redeemed1 = redemptionHistoryMapper.isCodeRedeemed(code2);

        Assert.assertEquals(true, redeemed0);
        Assert.assertEquals(false, redeemed1);
    }

}
