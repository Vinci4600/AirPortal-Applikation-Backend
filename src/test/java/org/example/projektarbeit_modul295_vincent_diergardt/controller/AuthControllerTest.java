package org.example.projektarbeit_modul295_vincent_diergardt.controller;

import org.example.projektarbeit_modul295_vincent_diergardt.dto.LoginRequestDTO;
import org.example.projektarbeit_modul295_vincent_diergardt.dto.RegisterRequestDTO;
import org.example.projektarbeit_modul295_vincent_diergardt.model.AppUser;
import org.example.projektarbeit_modul295_vincent_diergardt.model.Role;
import org.example.projektarbeit_modul295_vincent_diergardt.service.AppUserService;
import org.example.projektarbeit_modul295_vincent_diergardt.service.JwtService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class AuthControllerTest {

    @Mock
    private AppUserService appUserService;
    @Mock
    private JwtService jwtService;

    @InjectMocks
    private AuthController authController;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void afterEight() throws Exception {
        verifyNoMoreInteractions(appUserService, jwtService);
        closeable.close();
    }

    @Test
    void register_Success() {
        RegisterRequestDTO request = new RegisterRequestDTO();
        request.setUsername("user");
        request.setEmail("email@test.com");
        request.setPassword("pass");

        AppUser user = new AppUser("user", "email@test.com", "hash", Role.USER);
        user.setId(1L);

        when(appUserService.registerUser(anyString(), anyString(), anyString(), any(Role.class))).thenReturn(user);

        ResponseEntity<?> response = authController.register(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(appUserService).registerUser("user", "email@test.com", "pass", Role.USER);
    }

    @Test
    void login_Success() {
        LoginRequestDTO request = new LoginRequestDTO();
        request.setUsernameOrEmail("user");
        request.setPassword("pass");

        AppUser user = new AppUser("user", "email", "hash", Role.USER);
        user.setId(1L);

        when(appUserService.findByUsername("user")).thenReturn(Optional.of(user));
        when(appUserService.authenticateUser("user", "pass")).thenReturn(Optional.of(user));
        when(jwtService.generateToken("user", "USER")).thenReturn("token");

        ResponseEntity<?> response = authController.login(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(appUserService).findByUsername("user");
        verify(appUserService).authenticateUser("user", "pass");
        verify(jwtService).generateToken("user", "USER");
    }

    @Test
    void testEndpoint() {
        ResponseEntity<String> response = authController.test();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Auth Controller funktioniert!", response.getBody());
    }
}
