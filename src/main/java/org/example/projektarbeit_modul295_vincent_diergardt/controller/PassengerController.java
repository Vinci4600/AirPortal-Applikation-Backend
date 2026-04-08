package org.example.projektarbeit_modul295_vincent_diergardt.controller;

import org.example.projektarbeit_modul295_vincent_diergardt.model.Passenger;
import org.example.projektarbeit_modul295_vincent_diergardt.repository.PassengerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Der Type Passenger controller.
 */
@RestController
@RequestMapping("/api/passengers")
@CrossOrigin(origins = "http://localhost:5173")
public class PassengerController {
    private final PassengerRepository passengerRepository;

    /**
     * Erstellt einen neuen  Passenger controller.
     *
     * @param passengerRepository the passenger repository
     */
    public PassengerController(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    /**
     * Gibt alle Passagieren aus der Liste(Datenbank) an.
     *
     * @return the all passengers
     */
    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public List<Passenger> getAllPassengers() {
        return passengerRepository.findAll();
    }

    /**
     * Erstellt einen neuen Passagier  der dann im Repository(Schnittstelle zur Datenbank) gespeichert wird.
     *
     * @param passenger the passenger
     * @return the passenger
     */
    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public Passenger createPassenger(@RequestBody Passenger passenger) {
        return passengerRepository.save(passenger);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deletePassenger(@PathVariable Long id) {

        if (!passengerRepository.existsById(id)) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Passenger not found");
        }

        passengerRepository.deleteById(id);

        return ResponseEntity.ok("Passenger deleted successfully");
    }
}
