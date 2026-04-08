package org.example.projektarbeit_modul295_vincent_diergardt.controller;

import org.example.projektarbeit_modul295_vincent_diergardt.dto.PassengerDTO;
import org.example.projektarbeit_modul295_vincent_diergardt.service.PassengerService;
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

class PassengerControllerTest {

    @Mock
    private PassengerService passengerService;

    @InjectMocks
    private PassengerController passengerController;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void afterEight() throws Exception {
        verifyNoMoreInteractions(passengerService);
        closeable.close();
    }

    @Test
    void getAllPassengers() {
        PassengerDTO dto = new PassengerDTO();
        dto.setFirstname("Vincent");
        when(passengerService.getAllPassengers()).thenReturn(Arrays.asList(dto));

        List<PassengerDTO> result = passengerController.getAllPassengers();

        assertEquals(1, result.size());
        assertEquals("Vincent", result.get(0).getFirstname());
        verify(passengerService).getAllPassengers();
    }

    @Test
    void createPassenger() {
        PassengerDTO dto = new PassengerDTO();
        dto.setFirstname("Vincent");
        when(passengerService.createPassenger(any())).thenReturn(dto);

        PassengerDTO result = passengerController.createPassenger(dto);

        assertNotNull(result);
        verify(passengerService).createPassenger(any());
    }

    @Test
    void deletePassenger_Success() {
        when(passengerService.existsById(1L)).thenReturn(true);

        ResponseEntity<String> response = passengerController.deletePassenger(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(passengerService).existsById(1L);
        verify(passengerService).deletePassenger(1L);
    }

    @Test
    void deletePassenger_NotFound() {
        when(passengerService.existsById(1L)).thenReturn(false);

        ResponseEntity<String> response = passengerController.deletePassenger(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(passengerService).existsById(1L);
    }
}
