package org.example.projektarbeit_modul295_vincent_diergardt.controller;

import org.example.projektarbeit_modul295_vincent_diergardt.model.Booking;
import org.example.projektarbeit_modul295_vincent_diergardt.repository.BookingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Der type Booking controller.
 */
@RestController
@RequestMapping("/api/booking")
public class BookingController {
    private final BookingRepository bookingRepository;

    /**
     * Erstellt einen neuen Booking Controller.
     *
     * @param bookingRepository the booking repository
     */
    public BookingController(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    /**
     * Gibt Alle Buchungen aus die in der Liste gespeichert sind(Datenbank)
     *
     * @return the alle Buchungen
     */
    @GetMapping("/all")
    public List<Booking> getAllBookings() {//Buchungen anschauen
        return bookingRepository.findAll();
    }

    /**
     * Erstellt eine neue Buchung die dann in die Liste Hinzugefügt wird
     *
     * @param booking the booking
     * @return return Booking
     */
    @PostMapping("/add")//Buchung Hinzufügen
    public Booking createBooking(@RequestBody Booking booking) {
        return bookingRepository.save(booking);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBooking(@PathVariable Long id) {

        if (!bookingRepository.existsById(id)) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Booking not found");
        }

        bookingRepository.deleteById(id);

        return ResponseEntity.ok("Booking deleted successfully");
    }
}
