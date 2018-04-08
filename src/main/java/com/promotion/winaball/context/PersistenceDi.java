package com.promotion.winaball.context;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.io.Reader;

@MapperScan("com.promotion.winaball.persistence")
@Configuration
public class PersistenceDi {

    @Bean
    @Primary
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        final String resource = "mybatis-config.xml";
        try (final Reader reader = Resources.getResourceAsReader(resource)) {
            final SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
            return factory;
        }
    }

    @Bean
    public DataSourceTransactionManager transactionManager(final DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
