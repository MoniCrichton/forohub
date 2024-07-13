package com.alura.forohub;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class JwtSecretVerifier implements CommandLineRunner {

    @Value("${jwt.secret:defaultSecret}")
    private String jwtSecret;

    @Override
    public void run(String... args) throws Exception {
        if ("defaultSecret".equals(jwtSecret)) {
            System.out.println("JWT Secret is not set correctly!");
        } else {
            System.out.println("JWT Secret: " + jwtSecret);
        }
    }
}
