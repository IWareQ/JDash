package ru.dragonestia.jdash.config;

import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.sql2o.Sql2o;

@Configuration
public class DatabaseContext {

    @Bean
    @SneakyThrows
    public Sql2o getSql2o() {
        Class.forName("org.mariadb.jdbc.Driver");

        return new Sql2o("jdbc:mariadb://localhost:3305/gd", "root", "2986");
    }
}
