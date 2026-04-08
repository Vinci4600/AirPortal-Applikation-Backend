package org.example.projektarbeit_modul295_vincent_diergardt.service;

import org.example.projektarbeit_modul295_vincent_diergardt.dto.BookingDTO;
import org.example.projektarbeit_modul295_vincent_diergardt.model.Booking;
import org.example.projektarbeit_modul295_vincent_diergardt.model.Flight;
import org.example.projektarbeit_modul295_vincent_diergardt.model.Passenger;
import org.example.projektarbeit_modul295_vincent_diergardt.repository.BookingRepository;
import org.example.projektarbeit_modul295_vincent_diergardt.repository.FlightRepository;
import org.example.projektarbeit_modul295_vincent_diergardt.repository.PassengerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;
    @Mock
    private FlightRepository flightRepository;
    @Mock
    private PassengerRepository passengerRepository;

    @InjectMocks
    private BookingService bookingService;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void afterEight() throws Exception {
        verifyNoMoreInteractions(bookingRepository, flightRepository, passengerRepository);
        closeable.close();
    }

    @Test
    void getAllBookings() {
        Booking b1 = new Booking();
        b1.setId(1L);
        b1.setBookingDate(LocalDateTime.now());

        when(bookingRepository.findAll()).thenReturn(Arrays.asList(b1));

        List<BookingDTO> result = bookingService.getAllBookings();

        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
        verify(bookingRepository).findAll();
    }

    @Test
    void createBooking() {
        BookingDTO dto = new BookingDTO();
        dto.setBookingDate(LocalDateTime.now());
        dto.setFlightId(1L);
        dto.setPassengerId(2L);

        Flight flight = new Flight();
        flight.setFlight_id(1L);
        Passenger passenger = new Passenger();
        passenger.setId(2L);

        Booking booking = new Booking();
        booking.setId(100L);
        booking.setBookingDate(dto.getBookingDate());

        when(flightRepository.findById(1L)).thenReturn(Optional.of(flight));
        when(passengerRepository.findById(2L)).thenReturn(Optional.of(passenger));
        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);

        BookingDTO result = bookingService.createBooking(dto);

        assertNotNull(result);
        assertEquals(100L, result.getId());
        verify(flightRepository).findById(1L);
        verify(passengerRepository).findById(2L);
        verify(bookingRepository).save(any(Booking.class));
    }

    @Test
    void deleteBooking() {
        bookingService.deleteBooking(1L);
        verify(bookingRepository).deleteById(1L);
    }

    @Test
    void existsById() {
        when(bookingRepository.existsById(1L)).thenReturn(true);
        boolean exists = bookingService.existsById(1L);
        assertTrue(exists);
        verify(bookingRepository).existsById(1L);
    }
}
