package org.example.projektarbeit_modul295_vincent_diergardt.controller;

import org.example.projektarbeit_modul295_vincent_diergardt.dto.AircraftDTO;
import org.example.projektarbeit_modul295_vincent_diergardt.service.AircraftService;
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

class AircraftControllerTest {

    @Mock
    private AircraftService aircraftService;

    @InjectMocks
    private AircraftController aircraftController;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void afterEight() throws Exception {
        verifyNoMoreInteractions(aircraftService);
        closeable.close();
    }

    @Test
    void getAllAircrafts() {
        AircraftDTO dto = new AircraftDTO();
        dto.setModel("A320");
        when(aircraftService.getAllAircrafts()).thenReturn(Arrays.asList(dto));

        List<AircraftDTO> result = aircraftController.getAllAircrafts();

        assertEquals(1, result.size());
        assertEquals("A320", result.get(0).getModel());
        verify(aircraftService).getAllAircrafts();
    }

    @Test
    void createAircraft() {
        AircraftDTO dto = new AircraftDTO();
        dto.setModel("A320");
        when(aircraftService.createAircraft(any())).thenReturn(dto);

        AircraftDTO result = aircraftController.createAircraft(dto);

        assertNotNull(result);
        verify(aircraftService).createAircraft(any());
    }

    @Test
    void deleteAircraft_Success() {
        when(aircraftService.existsById(1L)).thenReturn(true);

        ResponseEntity<String> response = aircraftController.deleteAircraft(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(aircraftService).existsById(1L);
        verify(aircraftService).deleteAircraft(1L);
    }

    @Test
    void deleteAircraft_NotFound() {
        when(aircraftService.existsById(1L)).thenReturn(false);

        ResponseEntity<String> response = aircraftController.deleteAircraft(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(aircraftService).existsById(1L);
    }
}
