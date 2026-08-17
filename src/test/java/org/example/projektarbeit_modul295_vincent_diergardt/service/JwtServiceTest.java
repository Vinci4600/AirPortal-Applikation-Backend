package org.example.projektarbeit_modul295_vincent_diergardt.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

class JwtServiceTest {

    private JwtService testee;

    private static final String VALID_SECRET = "this-is-a-test-secret-of-32-chars!!";
    private static final long VALID_EXPIRATION = 3600000L; // 1 hour

    @BeforeEach
    void setUp() {
        testee = new JwtService();
        ReflectionTestUtils.setField(testee, "secretKey", VALID_SECRET);
        ReflectionTestUtils.setField(testee, "expirationTime", VALID_EXPIRATION);
        testee.validateConfig();
    }

    @Test
    void generateToken_containsUsernameAndRole() {
        String token = testee.generateToken("alice", "ADMIN");

        assertNotNull(token);
        assertEquals("alice", testee.extractUsername(token));
        assertEquals("ADMIN", testee.extractRole(token));
    }

    @Test
    void validateToken_validToken_returnsTrue() {
        String token = testee.generateToken("bob", "USER");
        assertTrue(testee.validateToken(token, "bob"));
    }

    @Test
    void validateToken_wrongUsername_returnsFalse() {
        String token = testee.generateToken("bob", "USER");
        assertFalse(testee.validateToken(token, "alice"));
    }

    @Test
    void validateConfig_shortSecret_throwsIllegalState() {
        JwtService bad = new JwtService();
        ReflectionTestUtils.setField(bad, "secretKey", "tooshort");
        ReflectionTestUtils.setField(bad, "expirationTime", VALID_EXPIRATION);
        assertThrows(IllegalStateException.class, bad::validateConfig);
    }

    @Test
    void validateConfig_negativeExpiration_throwsIllegalState() {
        JwtService bad = new JwtService();
        ReflectionTestUtils.setField(bad, "secretKey", VALID_SECRET);
        ReflectionTestUtils.setField(bad, "expirationTime", -1L);
        assertThrows(IllegalStateException.class, bad::validateConfig);
    }

    @Test
    void validateConfig_blankSecret_throwsIllegalState() {
        JwtService bad = new JwtService();
        ReflectionTestUtils.setField(bad, "secretKey", "   ");
        ReflectionTestUtils.setField(bad, "expirationTime", VALID_EXPIRATION);
        assertThrows(IllegalStateException.class, bad::validateConfig);
    }
}
