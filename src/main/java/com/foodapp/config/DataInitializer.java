package com.foodapp.config;

import com.foodapp.entity.User;
import com.foodapp.entity.enums.Role;
import com.foodapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,

            @Value("${app.admin.email}") String adminEmail,
            @Value("${app.admin.password}") String adminPassword,

            @Value("${app.delivery-partner.email}") String deliveryEmail,
            @Value("${app.delivery-partner.password}") String deliveryPassword
    ) {
        return args -> {

            if (!userRepository.existsByEmail(adminEmail)) {
                User admin = User.builder()
                        .name("Admin")
                        .email(adminEmail.trim().toLowerCase())
                        .password(passwordEncoder.encode(adminPassword))
                        .role(Role.ADMIN)
                        .enabled(true)
                        .build();
                userRepository.save(admin);
            }

            if (!userRepository.existsByEmail(deliveryEmail)) {

                User deliveryPartner = User.builder()
                        .name("Delivery Partner")
                        .email(deliveryEmail.trim().toLowerCase())
                        .password(passwordEncoder.encode(deliveryPassword))
                        .role(Role.DELIVERY_PARTNER)
                        .enabled(true)
                        .build();
                userRepository.save(deliveryPartner);
            }
        };
    }
}