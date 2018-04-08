package com.promotion.winaball.persistence;

import com.promotion.winaball.testutils.TestDatabaseInitializer;
import com.promotion.winaball.persistence.entity.TerritoryEntity;
import com.promotion.winaball.persistence.entity.TerritoryStatusEntity;
import com.promotion.winaball.persistence.mapper.TerritoryMapper;
import com.promotion.winaball.persistence.mapper.TerritoryStatusMapper;
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
import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TerritoryPersistanceTest {
    private static final String TERRITORY_GERMANY = "Germany";
    private static final long TERRITORY_ID_GERMANY = 1;

    @Autowired
    private DataSource dataSource;
    @Autowired
    private TerritoryMapper territoryMapper;
    @Autowired
    private TerritoryStatusMapper territoryStatusMapper;

    @Before
    public void setup() throws IOException, SQLException {
        new TestDatabaseInitializer().initialize(dataSource);
    }

    @Test
    public void testTerritoryGet() {
        final TerritoryEntity expected = new TerritoryEntity();
        expected.setId(1L);
        expected.setName(TERRITORY_GERMANY);
        expected.setWinPeriod(40);
        expected.setMaxBalls(10000);
        expected.setMaxBallsPerDay(250);

        final TerritoryEntity actual = territoryMapper.getTerritory(TERRITORY_GERMANY);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testTerritoryStatusPersistence() {
        // expected initial value
        LocalDateTime timestamp = LocalDateTime.of(2000, 1, 1, 1, 1, 1);
        final TerritoryStatusEntity territoryStatusEntity = new TerritoryStatusEntity();
        territoryStatusEntity.setTerritoryId(TERRITORY_ID_GERMANY);
        territoryStatusEntity.setRedeemsAfterWin(0);
        territoryStatusEntity.setWinsToday(0);
        territoryStatusEntity.setWinsTotal(0);
        territoryStatusEntity.setModifiedAt(timestamp);

        // read record just added
        final TerritoryStatusEntity territoryStatusEntityFromDb = territoryStatusMapper.getStatus(TERRITORY_ID_GERMANY);
        // timestamp is not tested
        territoryStatusEntityFromDb.setModifiedAt(timestamp);
        Assert.assertEquals(territoryStatusEntity, territoryStatusEntityFromDb);

        // update record
        territoryStatusEntity.setWinsTotal(10);
        territoryStatusMapper.updateStatus(territoryStatusEntity);

        // read updated record back
        final TerritoryStatusEntity updatedTerritoryStatusEntityFromDb = territoryStatusMapper.getStatus(TERRITORY_ID_GERMANY);
        // timestamp is not tested
        updatedTerritoryStatusEntityFromDb.setModifiedAt(timestamp);
        Assert.assertEquals(territoryStatusEntity, updatedTerritoryStatusEntityFromDb);
    }
}
