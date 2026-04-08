package org.example.projektarbeit_modul295_vincent_diergardt.service;

import org.example.projektarbeit_modul295_vincent_diergardt.dto.PassengerDTO;
import org.example.projektarbeit_modul295_vincent_diergardt.model.Passenger;
import org.example.projektarbeit_modul295_vincent_diergardt.repository.PassengerRepository;
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

class PassengerServiceTest {

    @Mock
    private PassengerRepository passengerRepository;

    @InjectMocks
    private PassengerService passengerService;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void afterEight() throws Exception {
        verifyNoMoreInteractions(passengerRepository);
        closeable.close();
    }

    @Test
    void getAllPassengers() {
        Passenger p1 = new Passenger();
        p1.setId(1L);
        p1.setFirstname("Vincent");
        
        Passenger p2 = new Passenger();
        p2.setId(2L);
        p2.setFirstname("John");

        when(passengerRepository.findAll()).thenReturn(Arrays.asList(p1, p2));

        List<PassengerDTO> result = passengerService.getAllPassengers();

        assertEquals(2, result.size());
        assertEquals("Vincent", result.get(0).getFirstname());
        verify(passengerRepository).findAll();
    }

    @Test
    void createPassenger() {
        PassengerDTO dto = new PassengerDTO();
        dto.setFirstname("Vincent");
        dto.setLastname("Diergardt");
        dto.setEmail("vincent@example.com");

        Passenger passenger = new Passenger();
        passenger.setId(1L);
        passenger.setFirstname("Vincent");

        when(passengerRepository.save(any(Passenger.class))).thenReturn(passenger);

        PassengerDTO result = passengerService.createPassenger(dto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(passengerRepository).save(any(Passenger.class));
    }

    @Test
    void existsById() {
        when(passengerRepository.existsById(1L)).thenReturn(true);
        boolean exists = passengerService.existsById(1L);
        assertTrue(exists);
        verify(passengerRepository).existsById(1L);
    }

    @Test
    void deletePassenger() {
        passengerService.deletePassenger(1L);
        verify(passengerRepository).deleteById(1L);
    }
}
