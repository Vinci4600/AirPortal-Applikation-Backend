package org.example.projektarbeit_modul295_vincent_diergardt.service;

import org.example.projektarbeit_modul295_vincent_diergardt.model.AppUser;
import org.example.projektarbeit_modul295_vincent_diergardt.model.Role;
import org.example.projektarbeit_modul295_vincent_diergardt.repository.AppUserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AppUserServiceTest {

    @Mock
    private AppUserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AppUserService appUserService;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void afterEight() throws Exception {
        verifyNoMoreInteractions(userRepository, passwordEncoder);
        closeable.close();
    }

    @Test
    void registerUser_Success() {
        String username = "testuser";
        String email = "test@example.com";
        String password = "password";
        Role role = Role.USER;

        when(userRepository.existsByUsername(username)).thenReturn(false);
        when(userRepository.existsByEmail(email)).thenReturn(false);
        when(passwordEncoder.encode(password)).thenReturn("hashedPassword");
        when(userRepository.save(any(AppUser.class))).thenAnswer(invocation -> invocation.getArgument(0));

        AppUser result = appUserService.registerUser(username, email, password, role);

        assertNotNull(result);
        assertEquals(username, result.getUsername());
        assertEquals("hashedPassword", result.getPassword());
        verify(userRepository).existsByUsername(username);
        verify(userRepository).existsByEmail(email);
        verify(passwordEncoder).encode(password);
        verify(userRepository).save(any(AppUser.class));
    }

    @Test
    void registerUser_UsernameExists() {
        when(userRepository.existsByUsername("existing")).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> 
            appUserService.registerUser("existing", "email", "pass", Role.USER)
        );

        verify(userRepository).existsByUsername("existing");
    }

    @Test
    void authenticateUser_Success() {
        AppUser user = new AppUser("user", "email", "hashed", Role.USER);
        when(userRepository.findByUsername("user")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("pass", "hashed")).thenReturn(true);

        Optional<AppUser> result = appUserService.authenticateUser("user", "pass");

        assertTrue(result.isPresent());
        verify(userRepository).findByUsername("user");
        verify(passwordEncoder).matches("pass", "hashed");
    }

    @Test
    void findByUsername() {
        appUserService.findByUsername("user");
        verify(userRepository).findByUsername("user");
    }

    @Test
    void findByEmail() {
        appUserService.findByEmail("email");
        verify(userRepository).findByEmail("email");
    }
}
