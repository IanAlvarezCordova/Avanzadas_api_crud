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
        String jdbcUrl = databaseUrl;
        if (databaseUrl.startsWith("postgresql://")) {
            // Transformar postgresql:// a jdbc:postgresql://
            jdbcUrl = "jdbc:postgresql://" + databaseUrl.substring("postgresql://".length());
            // Añadir puerto :5432 después del host, antes del nombre de la base de datos
            int credentialsEnd = jdbcUrl.indexOf("@");
            int dbNameStart = jdbcUrl.lastIndexOf("/");
            if (credentialsEnd != -1 && dbNameStart != -1 && dbNameStart > credentialsEnd) {
                String hostPart = jdbcUrl.substring(0, dbNameStart);
                String dbPart = jdbcUrl.substring(dbNameStart);
                // Verificar si ya hay un puerto especificado
                if (!hostPart.substring(credentialsEnd).contains(":")) {
                    jdbcUrl = hostPart + ":5432" + dbPart;
                }
            }
        }
        return DataSourceBuilder.create()
                .url(jdbcUrl)
                .driverClassName("org.postgresql.Driver")
                .build();
    }
}