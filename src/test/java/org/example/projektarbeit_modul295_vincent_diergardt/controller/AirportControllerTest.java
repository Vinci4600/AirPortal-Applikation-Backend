package org.example.projektarbeit_modul295_vincent_diergardt.controller;

import org.example.projektarbeit_modul295_vincent_diergardt.dto.AirportDTO;
import org.example.projektarbeit_modul295_vincent_diergardt.service.AirportService;
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

class AirportControllerTest {

    @Mock
    private AirportService airportService;

    @InjectMocks
    private AirportController airportController;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void afterEight() throws Exception {
        verifyNoMoreInteractions(airportService);
        closeable.close();
    }

    @Test
    void getAllAirports() {
        AirportDTO dto = new AirportDTO();
        dto.setName("Zürich");
        when(airportService.getAllAirports()).thenReturn(Arrays.asList(dto));

        List<AirportDTO> result = airportController.getAllAirports();

        assertEquals(1, result.size());
        assertEquals("Zürich", result.get(0).getName());
        verify(airportService).getAllAirports();
    }

    @Test
    void createAirport() {
        AirportDTO dto = new AirportDTO();
        dto.setName("ZRH");
        when(airportService.createAirport(any())).thenReturn(dto);

        AirportDTO result = airportController.createAirport(dto);

        assertNotNull(result);
        verify(airportService).createAirport(any());
    }

    @Test
    void deleteAirport_Success() {
        when(airportService.existsById(1L)).thenReturn(true);

        ResponseEntity<String> response = airportController.deleteAirport(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(airportService).existsById(1L);
        verify(airportService).deleteAirport(1L);
    }

    @Test
    void deleteAirport_NotFound() {
        when(airportService.existsById(1L)).thenReturn(false);

        ResponseEntity<String> response = airportController.deleteAirport(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(airportService).existsById(1L);
    }
}
