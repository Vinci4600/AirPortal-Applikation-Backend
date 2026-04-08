package org.example.projektarbeit_modul295_vincent_diergardt.controller;

import org.example.projektarbeit_modul295_vincent_diergardt.dto.AircraftDTO;
import org.example.projektarbeit_modul295_vincent_diergardt.service.AircraftService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Der Type Aircraft controller.
 */
@RestController
@RequestMapping("/api/aircrafts")
@CrossOrigin(origins = "http://localhost:5173")
public class AircraftController {
    private final AircraftService aircraftService;

    /**
     * Erstellt ein neues Aircraft Controller.
     *
     * @param aircraftService the aircraft service
     */
    public AircraftController(AircraftService aircraftService) {
        this.aircraftService = aircraftService;
    }

    /**
     * Gibt alle Aircrafts an
     *
     * @return the all aircrafts
     */
    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public List<AircraftDTO> getAllAircrafts() {
        return aircraftService.getAllAircrafts();
    }

    /**
     * Erstellt neue Aircrafts
     *
     * @param aircraftDTO the aircraft dto
     * @return the aircraft dto
     */
    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public AircraftDTO createAircraft(@RequestBody AircraftDTO aircraftDTO) {
        return aircraftService.createAircraft(aircraftDTO);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteAircraft(@PathVariable Long id) {

        if (!aircraftService.existsById(id)) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Aircraft not found");
        }

        aircraftService.deleteAircraft(id);

        return ResponseEntity.ok("Aircraft deleted successfully");
    }
}
