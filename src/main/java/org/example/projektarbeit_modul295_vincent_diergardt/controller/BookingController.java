package org.example.projektarbeit_modul295_vincent_diergardt.controller;

import org.example.projektarbeit_modul295_vincent_diergardt.dto.BookingDTO;
import org.example.projektarbeit_modul295_vincent_diergardt.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Der Type Booking controller.
 */
@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "http://localhost:5173")
public class BookingController {
    private final BookingService bookingService;

    /**
     * Erstellt einen neuen Booking Controller
     *
     * @param bookingService the booking service
     */
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    /**
     * Gibt alle Buchungen an
     *
     * @return the all bookings
     */
    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public List<BookingDTO> getAllBookings() {
        return bookingService.getAllBookings();
    }

    /**
     * Erstellt neue Buchungen
     *
     * @param bookingDTO the booking dto
     * @return the booking dto
     */
    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public BookingDTO createBooking(@RequestBody BookingDTO bookingDTO) {
        return bookingService.createBooking(bookingDTO);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteBooking(@PathVariable Long id) {

        if (!bookingService.existsById(id)) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Booking not found");
        }

        bookingService.deleteBooking(id);

        return ResponseEntity.ok("Booking deleted successfully");
    }
}
