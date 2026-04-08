package org.example.projektarbeit_modul295_vincent_diergardt.service;

import org.example.projektarbeit_modul295_vincent_diergardt.dto.AircraftDTO;
import org.example.projektarbeit_modul295_vincent_diergardt.model.Aircraft;
import org.example.projektarbeit_modul295_vincent_diergardt.repository.AircraftRepository;
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

class AircraftServiceTest {

    @Mock
    private AircraftRepository aircraftRepository;

    @InjectMocks
    private AircraftService aircraftService;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void afterEight() throws Exception {
        verifyNoMoreInteractions(aircraftRepository);
        closeable.close();
    }

    @Test
    void getAllAircrafts() {
        Aircraft aircraft1 = new Aircraft();
        aircraft1.setId(1L);
        aircraft1.setModel("A320");
        
        Aircraft aircraft2 = new Aircraft();
        aircraft2.setId(2L);
        aircraft2.setModel("737");

        when(aircraftRepository.findAll()).thenReturn(Arrays.asList(aircraft1, aircraft2));

        List<AircraftDTO> result = aircraftService.getAllAircrafts();

        assertEquals(2, result.size());
        assertEquals("A320", result.get(0).getModel());
        verify(aircraftRepository).findAll();
    }

    @Test
    void createAircraft() {
        AircraftDTO dto = new AircraftDTO();
        dto.setModel("A320");
        dto.setManufacture("Airbus");

        Aircraft aircraft = new Aircraft();
        aircraft.setId(1L);
        aircraft.setModel("A320");

        when(aircraftRepository.save(any(Aircraft.class))).thenReturn(aircraft);

        AircraftDTO result = aircraftService.createAircraft(dto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(aircraftRepository).save(any(Aircraft.class));
    }

    @Test
    void existsById() {
        when(aircraftRepository.existsById(1L)).thenReturn(true);

        boolean exists = aircraftService.existsById(1L);

        assertTrue(exists);
        verify(aircraftRepository).existsById(1L);
    }

    @Test
    void deleteAircraft() {
        aircraftService.deleteAircraft(1L);
        verify(aircraftRepository).deleteById(1L);
    }
}
