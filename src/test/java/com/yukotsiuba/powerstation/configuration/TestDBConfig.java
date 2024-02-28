package com.yukotsiuba.powerstation.configuration;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@TestConfiguration
public class TestDBConfig {

    @Bean
    public DataSource testDataSource() {
        final DataSource dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:/db/power-stations/schema.sql")
                .addScript("classpath:/db/connectors/schema.sql")
                .addScript("classpath:/db/connectors/alter_table.sql")
                .build();
        return dataSource;
    }
}
