package com.example.api_crud.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    @Value("${DATABASE_URL:jdbc:postgresql://localhost:5432/api_crud?user=postgres&password=your_local_password}")
    private String databaseUrl;

    @Bean
    public DataSource dataSource() {
        // Transformar postgresql:// a jdbc:postgresql:// y añadir puerto 5432 si no está presente
        String jdbcUrl = databaseUrl.startsWith("postgresql://")
                ? "jdbc:postgresql://" + databaseUrl.substring("postgresql://".length())
                : databaseUrl;
        if (!jdbcUrl.contains(":")) {
            // Añadir puerto 5432 si no está especificado
            int dbNameIndex = jdbcUrl.lastIndexOf("/");
            if (dbNameIndex != -1) {
                jdbcUrl = jdbcUrl.substring(0, dbNameIndex) + ":5432" + jdbcUrl.substring(dbNameIndex);
            }
        }
        return DataSourceBuilder.create()
                .url(jdbcUrl)
                .driverClassName("org.postgresql.Driver")
                .build();
    }
}