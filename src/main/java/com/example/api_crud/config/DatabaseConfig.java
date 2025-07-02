package com.example.api_crud.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    @Value("${DATABASE_URL}")
    private String databaseUrl;

    @Bean
    public DataSource dataSource() {
        String jdbcUrl = databaseUrl.replace("postgresql://", "jdbc:postgresql://");
        return DataSourceBuilder.create()
                .url(jdbcUrl)
                .driverClassName("org.postgresql.Driver")
                .build();
    }
}