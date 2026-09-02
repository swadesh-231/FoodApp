package com.foodapp;

import com.foodapp.config.EnvConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FoodAppApplication {

    public static void main(String[] args) {
        EnvConfig.load();
        SpringApplication.run(FoodAppApplication.class, args);
    }

}
