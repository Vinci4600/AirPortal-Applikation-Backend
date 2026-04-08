package org.example.projektarbeit_modul295_vincent_diergardt.service;

import org.example.projektarbeit_modul295_vincent_diergardt.dto.FlightDTO;
import org.example.projektarbeit_modul295_vincent_diergardt.model.Aircraft;
import org.example.projektarbeit_modul295_vincent_diergardt.model.Airport;
import org.example.projektarbeit_modul295_vincent_diergardt.model.Flight;
import org.example.projektarbeit_modul295_vincent_diergardt.repository.AircraftRepository;
import org.example.projektarbeit_modul295_vincent_diergardt.repository.AirportRepository;
import org.example.projektarbeit_modul295_vincent_diergardt.repository.FlightRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class FlightServiceTest {

    @Mock
    private FlightRepository flightRepository;
    @Mock
    private AircraftRepository aircraftRepository;
    @Mock
    private AirportRepository airportRepository;

    @InjectMocks
    private FlightService flightService;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void afterEight() throws Exception {
        verifyNoMoreInteractions(flightRepository, aircraftRepository, airportRepository);
        closeable.close();
    }

    @Test
    void getAllFlights() {
        Flight f1 = new Flight();
        f1.setFlight_id(1L);
        f1.setFlightNumber("LX123");

        when(flightRepository.findAll()).thenReturn(Arrays.asList(f1));

        List<FlightDTO> result = flightService.getAllFlights();

        assertEquals(1, result.size());
        assertEquals("LX123", result.get(0).getFlightNumber());
        verify(flightRepository).findAll();
    }

    @Test
    void createFlight() {
        FlightDTO dto = new FlightDTO();
        dto.setFlightNumber("LX123");
        dto.setAircraftId(1L);
        dto.setDepartureAirportId(2L);
        dto.setArrivalAirportId(3L);

        Aircraft aircraft = new Aircraft();
        aircraft.setId(1L);
        Airport dep = new Airport();
        dep.setId(2L);
        Airport arr = new Airport();
        arr.setId(3L);

        Flight flight = new Flight();
        flight.setFlight_id(100L);
        flight.setFlightNumber("LX123");

        when(aircraftRepository.findById(1L)).thenReturn(Optional.of(aircraft));
        when(airportRepository.findById(2L)).thenReturn(Optional.of(dep));
        when(airportRepository.findById(3L)).thenReturn(Optional.of(arr));
        when(flightRepository.save(any(Flight.class))).thenReturn(flight);

        FlightDTO result = flightService.createFlight(dto);

        assertNotNull(result);
        assertEquals(100L, result.getId());
        verify(aircraftRepository).findById(1L);
        verify(airportRepository).findById(2L);
        verify(airportRepository).findById(3L);
        verify(flightRepository).save(any(Flight.class));
    }

    @Test
    void deleteFlight() {
        flightService.deleteFlight(1L);
        verify(flightRepository).deleteById(1L);
    }

    @Test
    void existsById() {
        when(flightRepository.existsById(1L)).thenReturn(true);
        boolean exists = flightService.existsById(1L);
        assertTrue(exists);
        verify(flightRepository).existsById(1L);
    }
}
