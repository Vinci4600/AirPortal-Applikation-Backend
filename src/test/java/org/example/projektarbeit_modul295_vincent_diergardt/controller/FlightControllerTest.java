package org.example.projektarbeit_modul295_vincent_diergardt.controller;

import org.example.projektarbeit_modul295_vincent_diergardt.dto.FlightDTO;
import org.example.projektarbeit_modul295_vincent_diergardt.service.FlightService;
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

class FlightControllerTest {

    @Mock
    private FlightService flightService;

    @InjectMocks
    private FlightController flightController;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void afterEight() throws Exception {
        verifyNoMoreInteractions(flightService);
        closeable.close();
    }

    @Test
    void getAllFlights() {
        FlightDTO dto = new FlightDTO();
        dto.setFlightNumber("LX123");
        when(flightService.getAllFlights()).thenReturn(Arrays.asList(dto));

        List<FlightDTO> result = flightController.getAllFlights();

        assertEquals(1, result.size());
        assertEquals("LX123", result.get(0).getFlightNumber());
        verify(flightService).getAllFlights();
    }

    @Test
    void createFlight() {
        FlightDTO dto = new FlightDTO();
        dto.setFlightNumber("LX123");
        when(flightService.createFlight(any())).thenReturn(dto);

        FlightDTO result = flightController.createFlight(dto);

        assertNotNull(result);
        verify(flightService).createFlight(any());
    }

    @Test
    void deleteFlight_Success() {
        when(flightService.existsById(1L)).thenReturn(true);

        ResponseEntity<String> response = flightController.deleteFlight(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(flightService).existsById(1L);
        verify(flightService).deleteFlight(1L);
    }

    @Test
    void deleteFlight_NotFound() {
        when(flightService.existsById(1L)).thenReturn(false);

        ResponseEntity<String> response = flightController.deleteFlight(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(flightService).existsById(1L);
    }
}
