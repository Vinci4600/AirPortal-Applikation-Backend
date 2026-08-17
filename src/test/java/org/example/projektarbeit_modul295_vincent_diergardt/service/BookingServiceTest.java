package org.example.projektarbeit_modul295_vincent_diergardt.service;

import org.example.projektarbeit_modul295_vincent_diergardt.dto.BookingDTO;
import org.example.projektarbeit_modul295_vincent_diergardt.exception.ResourceNotFoundException;
import org.example.projektarbeit_modul295_vincent_diergardt.model.Booking;
import org.example.projektarbeit_modul295_vincent_diergardt.model.Flight;
import org.example.projektarbeit_modul295_vincent_diergardt.model.Passenger;
import org.example.projektarbeit_modul295_vincent_diergardt.repository.BookingRepository;
import org.example.projektarbeit_modul295_vincent_diergardt.repository.FlightRepository;
import org.example.projektarbeit_modul295_vincent_diergardt.repository.PassengerRepository;
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
class BookingServiceTest {

    @InjectMocks
    private BookingService testee;

    @Mock
    private BookingRepository bookingRepository;
    @Mock
    private FlightRepository flightRepository;
    @Mock
    private PassengerRepository passengerRepository;

    @AfterEach
    void afterEach() {
        verifyNoMoreInteractions(bookingRepository, flightRepository, passengerRepository);
    }

    @Test
    void getAllBookings_returnsEmptyList() {
        when(bookingRepository.findAll()).thenReturn(List.of());
        List<BookingDTO> result = testee.getAllBookings();
        assertEquals(0, result.size());
        verify(bookingRepository).findAll();
    }

    @Test
    void createBooking_success() {
        BookingDTO dto = new BookingDTO(null, 1L, 2L, LocalDateTime.now());

        Flight flight = new Flight();
        flight.setFlight_id(1L);
        Passenger passenger = new Passenger();
        passenger.setId(2L);

        Booking saved = new Booking();
        saved.setId(10L);
        saved.setFlight(flight);
        saved.setPassenger(passenger);
        saved.setBookingDate(dto.bookingDate());

        when(flightRepository.findById(1L)).thenReturn(Optional.of(flight));
        when(passengerRepository.findById(2L)).thenReturn(Optional.of(passenger));
        when(bookingRepository.save(any(Booking.class))).thenReturn(saved);

        BookingDTO result = testee.createBooking(dto);

        assertNotNull(result);
        assertEquals(10L, result.id());
        assertEquals(1L, result.flightId());
        assertEquals(2L, result.passengerId());
        verify(flightRepository).findById(1L);
        verify(passengerRepository).findById(2L);
        verify(bookingRepository).save(any(Booking.class));
    }

    @Test
    void createBooking_flightNotFound_throwsResourceNotFoundException() {
        BookingDTO dto = new BookingDTO(null, 99L, 2L, LocalDateTime.now());
        when(flightRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> testee.createBooking(dto));
        verify(flightRepository).findById(99L);
    }

    @Test
    void createBooking_passengerNotFound_throwsResourceNotFoundException() {
        BookingDTO dto = new BookingDTO(null, 1L, 99L, LocalDateTime.now());
        Flight flight = new Flight();
        when(flightRepository.findById(1L)).thenReturn(Optional.of(flight));
        when(passengerRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> testee.createBooking(dto));
        verify(flightRepository).findById(1L);
        verify(passengerRepository).findById(99L);
    }

    @Test
    void existsById_delegatesToRepository() {
        when(bookingRepository.existsById(5L)).thenReturn(true);
        assertTrue(testee.existsById(5L));
        verify(bookingRepository).existsById(5L);
    }
}
