package ru.dragonestia.jdash;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JDashApplication {

    public static final String DATABASE_ADDRESS = "/gd-database";
    public static final boolean DEBUG = true;

    public static void main(String[] args) {
        SpringApplication.run(JDashApplication.class, args);
    }
}
