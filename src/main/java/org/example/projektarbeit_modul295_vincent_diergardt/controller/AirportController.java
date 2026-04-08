package org.example.projektarbeit_modul295_vincent_diergardt.controller;

import org.example.projektarbeit_modul295_vincent_diergardt.dto.AirportDTO;
import org.example.projektarbeit_modul295_vincent_diergardt.service.AirportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Der Type Airport controller.
 */
@RestController
@RequestMapping("/api/airports")
@CrossOrigin(origins = "http://localhost:5173")
public class AirportController {
    private final AirportService airportService;

    /**
     * Erstellt einen neuen Airport Controller
     *
     * @param airportService the airport service
     */
    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    /**
     * Gibt alle Flughäfen an
     *
     * @return the all airports
     */
    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public List<AirportDTO> getAllAirports() {
        return airportService.getAllAirports();
    }

    /**
     * Erstellt neue Flughäfen
     *
     * @param airportDTO the airport dto
     * @return the airport dto
     */
    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public AirportDTO createAirport(@RequestBody AirportDTO airportDTO) {
        return airportService.createAirport(airportDTO);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteAirport(@PathVariable Long id) {

        if (!airportService.existsById(id)) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Airport not found");
        }

        airportService.deleteAirport(id);

        return ResponseEntity.ok("Airport deleted successfully");
    }
}
