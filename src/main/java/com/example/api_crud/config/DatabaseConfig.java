package com.example.api_crud.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;

@Configuration
public class DatabaseConfig {

    @Value("${DATABASE_URL:jdbc:postgresql://localhost:5432/api_crud?user=postgres&password=admin}")
    private String databaseUrl;

    @Bean
    public DataSource dataSource() throws URISyntaxException {
        URI dbUri;
        try {
            // Si la URL empieza con "postgresql://", úsala directamente; si empieza con "jdbc:postgresql://", conviértela
            dbUri = new URI(databaseUrl.startsWith("postgresql://") ? databaseUrl : "postgresql://" + databaseUrl.substring("jdbc:postgresql://".length()));
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Invalid DATABASE_URL: " + databaseUrl, e);
        }

        // Extraer usuario y contraseña de la parte "userinfo" (antes del @)
        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];

        // Construir la URL JDBC con host, puerto (5432 por defecto si no está especificado) y nombre de la base de datos
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ":" + (dbUri.getPort() != -1 ? dbUri.getPort() : 5432) + dbUri.getPath();

        // Configurar el DataSource con la URL y las credenciales
        return DataSourceBuilder.create()
                .url(dbUrl)
                .username(username)
                .password(password)
                .driverClassName("org.postgresql.Driver")
                .build();
    }
}