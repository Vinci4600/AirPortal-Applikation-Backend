package org.example.projektarbeit_modul295_vincent_diergardt.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class JwtServiceTest {

    private JwtService jwtService;

    @BeforeEach
    void setUp() {
        jwtService = new JwtService();
        ReflectionTestUtils.setField(jwtService, "secretKey", "mySecretKeyForTestingWhichIsLongEnoughForHMAC256");
        ReflectionTestUtils.setField(jwtService, "expirationTime", 3600000L); // 1 hour
    }

    @Test
    void generateAndValidateToken() {
        String username = "testuser";
        String role = "USER";

        String token = jwtService.generateToken(username, role);

        assertNotNull(token);
        assertEquals(username, jwtService.extractUsername(token));
        assertEquals(role, jwtService.extractRole(token));
        assertTrue(jwtService.validateToken(token, username));
    }

    @Test
    void extractExpiration() {
        String token = jwtService.generateToken("user", "ROLE");
        Date expiration = jwtService.extractExpiration(token);
        assertTrue(expiration.after(new Date()));
    }
}
