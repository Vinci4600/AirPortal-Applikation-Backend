package org.example.projektarbeit_modul295_vincent_diergardt.controller;

import org.example.projektarbeit_modul295_vincent_diergardt.dto.LogisticUserDTO;
import org.example.projektarbeit_modul295_vincent_diergardt.service.LogisticUserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class LogisticUserControllerTest {

    @Mock
    private LogisticUserService logisticUserService;

    @InjectMocks
    private LogisticUserController logisticUserController;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void afterEight() throws Exception {
        verifyNoMoreInteractions(logisticUserService);
        closeable.close();
    }

    @Test
    void getAllLogisticUsers() {
        LogisticUserDTO dto = new LogisticUserDTO();
        dto.setFirstname("Vincent");
        when(logisticUserService.getAllLogisticUsers()).thenReturn(Arrays.asList(dto));

        List<LogisticUserDTO> result = logisticUserController.getAllLogisticUsers();

        assertEquals(1, result.size());
        assertEquals("Vincent", result.get(0).getFirstname());
        verify(logisticUserService).getAllLogisticUsers();
    }

    @Test
    void createLogisticUser() {
        LogisticUserDTO dto = new LogisticUserDTO();
        dto.setFirstname("Vincent");
        when(logisticUserService.createLogisticUser(any())).thenReturn(dto);

        LogisticUserDTO result = logisticUserController.createLogisticUser(dto);

        assertNotNull(result);
        verify(logisticUserService).createLogisticUser(any());
    }

    @Test
    void deleteLogisticUser_Success() {
        when(logisticUserService.existsById(1L)).thenReturn(true);

        ResponseEntity<String> response = logisticUserController.deleteLogisticUser(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(logisticUserService).existsById(1L);
        verify(logisticUserService).deleteLogisticUser(1L);
    }

    @Test
    void deleteLogisticUser_NotFound() {
        when(logisticUserService.existsById(1L)).thenReturn(false);

        ResponseEntity<String> response = logisticUserController.deleteLogisticUser(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(logisticUserService).existsById(1L);
    }
}
