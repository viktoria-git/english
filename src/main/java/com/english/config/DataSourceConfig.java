package com.english.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@EnableConfigurationProperties(VaultConfig.class)
public class DataSourceConfig {
    private final VaultConfig configuration;

    public DataSourceConfig(VaultConfig configuration) {
        this.configuration = configuration;
    }
    @Bean
    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url("jdbc:mysql://localhost:3306/english?characterEncoding=UTF-8");
        System.out.println(configuration.getLogin());
        System.out.println(configuration.getPassword());
        dataSourceBuilder.username(configuration.getLogin());
        dataSourceBuilder.password(configuration.getPassword());
        return dataSourceBuilder.build();
    }
}
