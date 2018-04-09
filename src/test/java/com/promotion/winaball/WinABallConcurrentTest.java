package com.promotion.winaball;

import com.promotion.winaball.service.CouponRedemptionService;
import com.promotion.winaball.service.dto.Customer;
import com.promotion.winaball.testutils.TestDatabaseInitializer;
import com.promotion.winaball.testutils.TestThreadFactory;
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
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WinABallConcurrentTest {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private CouponRedemptionService couponRedemptionService;

    @Before
    public void setup() throws IOException, SQLException {
        new TestDatabaseInitializer().initialize(dataSource);
    }

    @Test
    public void concurrentRedemptionTest() throws ExecutionException, InterruptedException {
        final ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2, new TestThreadFactory());

        final Customer customer1 = Customer.builder()
                .setName("Alf A. Romeo")
                .setEmailAddress("alf@asd.ca")
                .setDateOfBirth(LocalDate.of(2000,1,1))
                .build();
        final Future<?> future1 = fixedThreadPool.submit(new RedemptionAttemptRunnable(customer1, couponRedemptionService));

        final Customer customer2 = Customer.builder()
                .setName("Barry Cade")
                .setEmailAddress("barry@asd.ca")
                .setDateOfBirth(LocalDate.of(2000,1,1))
                .build();
        final Future<?> future2 = fixedThreadPool.submit(new RedemptionAttemptRunnable(customer2, couponRedemptionService));

        future1.get();
        future2.get();

        // assert every second attempt is winner
        Assert.assertEquals(false, RedemptionAttemptRunnable.getResults().get(0));
        Assert.assertEquals(true, RedemptionAttemptRunnable.getResults().get(1));
        Assert.assertEquals(false, RedemptionAttemptRunnable.getResults().get(2));
        Assert.assertEquals(true, RedemptionAttemptRunnable.getResults().get(3));
        Assert.assertEquals(false, RedemptionAttemptRunnable.getResults().get(4));
        Assert.assertEquals(true, RedemptionAttemptRunnable.getResults().get(5));
        Assert.assertEquals(false, RedemptionAttemptRunnable.getResults().get(6));
        Assert.assertEquals(true, RedemptionAttemptRunnable.getResults().get(7));
        Assert.assertEquals(false, RedemptionAttemptRunnable.getResults().get(8));
        Assert.assertEquals(true, RedemptionAttemptRunnable.getResults().get(9));
    }

}
