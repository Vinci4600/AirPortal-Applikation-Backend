package org.example.projektarbeit_modul295_vincent_diergardt.service;

import org.example.projektarbeit_modul295_vincent_diergardt.dto.AirportDTO;
import org.example.projektarbeit_modul295_vincent_diergardt.model.Airport;
import org.example.projektarbeit_modul295_vincent_diergardt.repository.AirportRepository;
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

class AirportServiceTest {

    @Mock
    private AirportRepository airportRepository;

    @InjectMocks
    private AirportService airportService;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void afterEight() throws Exception {
        verifyNoMoreInteractions(airportRepository);
        closeable.close();
    }

    @Test
    void getAllAirports() {
        Airport airport1 = new Airport();
        airport1.setId(1L);
        airport1.setName("Zürich");
        
        Airport airport2 = new Airport();
        airport2.setId(2L);
        airport2.setName("Geneva");

        when(airportRepository.findAll()).thenReturn(Arrays.asList(airport1, airport2));

        List<AirportDTO> result = airportService.getAllAirports();

        assertEquals(2, result.size());
        assertEquals("Zürich", result.get(0).getName());
        assertEquals("Geneva", result.get(1).getName());
        verify(airportRepository).findAll();
    }

    @Test
    void createAirport() {
        AirportDTO dto = new AirportDTO();
        dto.setName("Zürich");
        dto.setCountry("Switzerland");
        dto.setIataCode("ZRH");

        Airport airport = new Airport();
        airport.setId(1L);
        airport.setName("Zürich");
        airport.setCountry("Switzerland");
        airport.setIataCode("ZRH");

        when(airportRepository.save(any(Airport.class))).thenReturn(airport);

        AirportDTO result = airportService.createAirport(dto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Zürich", result.getName());
        verify(airportRepository).save(any(Airport.class));
    }

    @Test
    void existsById() {
        when(airportRepository.existsById(1L)).thenReturn(true);

        boolean exists = airportService.existsById(1L);

        assertTrue(exists);
        verify(airportRepository).existsById(1L);
    }

    @Test
    void deleteAirport() {
        airportService.deleteAirport(1L);
        verify(airportRepository).deleteById(1L);
    }
}
