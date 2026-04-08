package org.example.projektarbeit_modul295_vincent_diergardt.service;

import org.example.projektarbeit_modul295_vincent_diergardt.dto.LogisticUserDTO;
import org.example.projektarbeit_modul295_vincent_diergardt.model.LogisticUser;
import org.example.projektarbeit_modul295_vincent_diergardt.repository.LogisticUserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class LogisticUserServiceTest {

    @Mock
    private LogisticUserRepository logisticUserRepository;

    @InjectMocks
    private LogisticUserService logisticUserService;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void afterEight() throws Exception {
        verifyNoMoreInteractions(logisticUserRepository);
        closeable.close();
    }

    @Test
    void getAllLogisticUsers() {
        LogisticUser u1 = new LogisticUser();
        u1.setId(1L);
        u1.setFirstname("Vincent");

        when(logisticUserRepository.findAll()).thenReturn(Arrays.asList(u1));

        List<LogisticUserDTO> result = logisticUserService.getAllLogisticUsers();

        assertEquals(1, result.size());
        assertEquals("Vincent", result.get(0).getFirstname());
        verify(logisticUserRepository).findAll();
    }

    @Test
    void createLogisticUser() {
        LogisticUserDTO dto = new LogisticUserDTO();
        dto.setFirstname("Vincent");
        dto.setLastname("Diergardt");
        dto.setEmail("vincent@example.com");

        LogisticUser savedUser = new LogisticUser();
        savedUser.setId(1L);
        savedUser.setFirstname("Vincent");

        when(logisticUserRepository.save(any(LogisticUser.class))).thenReturn(savedUser);

        LogisticUserDTO result = logisticUserService.createLogisticUser(dto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(logisticUserRepository).save(any(LogisticUser.class));
    }

    @Test
    void existsById() {
        when(logisticUserRepository.existsById(1L)).thenReturn(true);
        boolean exists = logisticUserService.existsById(1L);
        assertTrue(exists);
        verify(logisticUserRepository).existsById(1L);
    }

    @Test
    void deleteLogisticUser() {
        logisticUserService.deleteLogisticUser(1L);
        verify(logisticUserRepository).deleteById(1L);
    }
}
