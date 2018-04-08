package com.promotion.winaball.testutils;

import org.apache.ibatis.jdbc.ScriptRunner;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

import static org.apache.ibatis.io.Resources.getResourceAsReader;

public class TestDatabaseInitializer {

    private static final String DROP_DB_SQL = "sql/drop-db.sql";
    private static final String CREATE_DB_SQL = "sql/create-db.sql";
    private static final String INIT_DB_SQL = "sql/init-db.sql";

    public void initialize(final DataSource dataSource) throws IOException, SQLException {
        final ScriptRunner runner = new ScriptRunner(dataSource.getConnection());
        runner.setAutoCommit(true);
        runner.setStopOnError(false);
        runner.runScript(getResourceAsReader(DROP_DB_SQL));
        runner.runScript(getResourceAsReader(CREATE_DB_SQL));
        runner.runScript(getResourceAsReader(INIT_DB_SQL));
        runner.closeConnection();
    }
}
