package org.example.projektarbeit_modul295_vincent_diergardt.service;

import org.example.projektarbeit_modul295_vincent_diergardt.dto.FlightDTO;
import org.example.projektarbeit_modul295_vincent_diergardt.exception.ResourceNotFoundException;
import org.example.projektarbeit_modul295_vincent_diergardt.model.Aircraft;
import org.example.projektarbeit_modul295_vincent_diergardt.model.Airport;
import org.example.projektarbeit_modul295_vincent_diergardt.model.Flight;
import org.example.projektarbeit_modul295_vincent_diergardt.repository.AircraftRepository;
import org.example.projektarbeit_modul295_vincent_diergardt.repository.AirportRepository;
import org.example.projektarbeit_modul295_vincent_diergardt.repository.FlightRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FlightServiceTest {

    @InjectMocks
    private FlightService testee;

    @Mock
    private FlightRepository flightRepository;
    @Mock
    private AircraftRepository aircraftRepository;
    @Mock
    private AirportRepository airportRepository;

    @AfterEach
    void afterEach() {
        verifyNoMoreInteractions(flightRepository, aircraftRepository, airportRepository);
    }

    @Test
    void getAllFlights_returnsEmptyList() {
        when(flightRepository.findAll()).thenReturn(List.of());
        assertEquals(0, testee.getAllFlights().size());
        verify(flightRepository).findAll();
    }

    @Test
    void createFlight_success() {
        LocalDateTime dep = LocalDateTime.now();
        LocalDateTime arr = dep.plusHours(2);
        FlightDTO dto = new FlightDTO(null, "LX100", dep, arr, 1L, 2L, 3L);

        Aircraft aircraft = new Aircraft();
        aircraft.setId(1L);
        Airport depAirport = new Airport();
        depAirport.setId(2L);
        depAirport.setName("Zurich");
        depAirport.setIataCode("ZRH");
        Airport arrAirport = new Airport();
        arrAirport.setId(3L);
        arrAirport.setName("London");
        arrAirport.setIataCode("LHR");

        Flight saved = new Flight();
        saved.setFlight_id(10L);
        saved.setFlightNumber("LX100");
        saved.setDepartureTime(dep);
        saved.setArrivalTime(arr);
        saved.setAircraft(aircraft);
        saved.setDepartureAirport(depAirport);
        saved.setArrivalAirport(arrAirport);

        when(aircraftRepository.findById(1L)).thenReturn(Optional.of(aircraft));
        when(airportRepository.findById(2L)).thenReturn(Optional.of(depAirport));
        when(airportRepository.findById(3L)).thenReturn(Optional.of(arrAirport));
        when(flightRepository.save(any(Flight.class))).thenReturn(saved);

        FlightDTO result = testee.createFlight(dto);

        assertNotNull(result);
        assertEquals(10L, result.id());
        assertEquals("LX100", result.flightNumber());
        verify(aircraftRepository).findById(1L);
        verify(airportRepository).findById(2L);
        verify(airportRepository).findById(3L);
        verify(flightRepository).save(any(Flight.class));
    }

    @Test
    void createFlight_aircraftNotFound_throwsResourceNotFoundException() {
        FlightDTO dto = new FlightDTO(null, "LX100", LocalDateTime.now(), LocalDateTime.now().plusHours(1), 99L, 1L, 2L);
        when(aircraftRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> testee.createFlight(dto));
        verify(aircraftRepository).findById(99L);
        // arrivalAirportId and departureAirportId lookups never reached
    }

    @Test
    void createFlight_departureAirportNotFound_throwsResourceNotFoundException() {
        FlightDTO dto = new FlightDTO(null, "LX100", LocalDateTime.now(), LocalDateTime.now().plusHours(1), 1L, 99L, 2L);
        Aircraft aircraft = new Aircraft();
        when(aircraftRepository.findById(1L)).thenReturn(Optional.of(aircraft));
        when(airportRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> testee.createFlight(dto));
        verify(aircraftRepository).findById(1L);
        verify(airportRepository).findById(99L);
        // arrivalAirportId lookup never reached
    }

    @Test
    void existsById_delegatesToRepository() {
        when(flightRepository.existsById(5L)).thenReturn(true);
        assertTrue(testee.existsById(5L));
        verify(flightRepository).existsById(5L);
    }
}
