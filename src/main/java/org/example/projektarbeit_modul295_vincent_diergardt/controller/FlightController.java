package org.example.projektarbeit_modul295_vincent_diergardt.controller;

import org.example.projektarbeit_modul295_vincent_diergardt.dto.FlightDTO;
import org.example.projektarbeit_modul295_vincent_diergardt.service.FlightService;
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
    private final FlightService flightService;


    /**
     * Erstellt einen neuen Flight Controller
     *
     * @param flightService the flight service
     */
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    /**
     * Gibt alle flüge an die in der liste(Datenbank) gespeichert sind
     *
     * @return the all flights
     */
    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public List<FlightDTO> getAllFlights() {
        return flightService.getAllFlights();
    }

    /**
     * Erstellt neue Flüge die danach in der liste gespeichert werden.
     *
     * @param flightDTO the flight dto
     * @return the flight dto
     */
    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public FlightDTO createFlight(@RequestBody FlightDTO flightDTO) {
        return flightService.createFlight(flightDTO);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteFlight(@PathVariable Long id) {

        if (!flightService.existsById(id)) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Flight not found");
        }

        flightService.deleteFlight(id);

        return ResponseEntity.ok("Flight deleted successfully");
    }
}
