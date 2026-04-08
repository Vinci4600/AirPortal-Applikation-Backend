package org.example.projektarbeit_modul295_vincent_diergardt.controller;

import org.example.projektarbeit_modul295_vincent_diergardt.model.Flight;
import org.example.projektarbeit_modul295_vincent_diergardt.repository.FlightRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Der Type Flight controller.
 */
@RestController
@RequestMapping("/api/flights")
@CrossOrigin(origins = "http://localhost:5173")
public class FlightController {
    private final FlightRepository flightRepository;


    /**
     * Erstellt einen neuen Flight Controller
     *
     * @param flightRepository the flight repository
     */
    public FlightController(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    /**
     * Gibt alle flüge an die in der liste(Datenbank) gespeichert sind
     *
     * @return the all flights
     */
    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    /**
     * Erstellt neue Flüge die danach in der liste gespeichert werden.
     *
     * @param flight the flight
     * @return the flight
     */
    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public Flight createFlight(@RequestBody Flight flight) {
        return flightRepository.save(flight);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteFlight(@PathVariable Long id) {

        if (!flightRepository.existsById(id)) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Flight not found");
        }

        flightRepository.deleteById(id);

        return ResponseEntity.ok("Flight deleted successfully");
    }
}
