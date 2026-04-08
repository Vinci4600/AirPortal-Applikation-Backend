package org.example.projektarbeit_modul295_vincent_diergardt.controller;

import org.example.projektarbeit_modul295_vincent_diergardt.dto.BookingDTO;
import org.example.projektarbeit_modul295_vincent_diergardt.service.BookingService;
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

class BookingControllerTest {

    @Mock
    private BookingService bookingService;

    @InjectMocks
    private BookingController bookingController;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void afterEight() throws Exception {
        verifyNoMoreInteractions(bookingService);
        closeable.close();
    }

    @Test
    void getAllBookings() {
        BookingDTO dto = new BookingDTO();
        dto.setId(1L);
        when(bookingService.getAllBookings()).thenReturn(Arrays.asList(dto));

        List<BookingDTO> result = bookingController.getAllBookings();

        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
        verify(bookingService).getAllBookings();
    }

    @Test
    void createBooking() {
        BookingDTO dto = new BookingDTO();
        dto.setId(1L);
        when(bookingService.createBooking(any())).thenReturn(dto);

        BookingDTO result = bookingController.createBooking(dto);

        assertNotNull(result);
        verify(bookingService).createBooking(any());
    }

    @Test
    void deleteBooking_Success() {
        when(bookingService.existsById(1L)).thenReturn(true);

        ResponseEntity<String> response = bookingController.deleteBooking(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(bookingService).existsById(1L);
        verify(bookingService).deleteBooking(1L);
    }

    @Test
    void deleteBooking_NotFound() {
        when(bookingService.existsById(1L)).thenReturn(false);

        ResponseEntity<String> response = bookingController.deleteBooking(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(bookingService).existsById(1L);
    }
}
