package org.example.projektarbeit_modul295_vincent_diergardt.controller;

import org.example.projektarbeit_modul295_vincent_diergardt.model.Aircraft;
import org.example.projektarbeit_modul295_vincent_diergardt.repository.AircraftRepository;
import org.example.projektarbeit_modul295_vincent_diergardt.repository.FlightRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;

/**
 * Der Type Aircraft controller.
 */
@RestController
@RequestMapping("/api/aircraft")
@CrossOrigin(origins = "http://localhost:5173")

public class AircraftController {
    private final AircraftRepository aircraftRepository;
    private final FlightRepository flightRepository;

    /**
     * Erstellt einen neuen Aircraft controller.
     *
     * @param aircraftRepository das aircraft repository
     */
    public AircraftController(AircraftRepository aircraftRepository, FlightRepository flightRepository) {
        this.aircraftRepository = aircraftRepository;
        this.flightRepository = flightRepository;
    }

    /**
     * Gibt alle Flugzeuge aus die in der datenbank enthalten sind
     *
     *
     */
    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public List<Aircraft> getAllAircraft() {
        return aircraftRepository.findAll();
    }

    /**
     *
     *
     * Fügt ein neues flugzeug Hinzu
     *
     */
    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public Aircraft createAircraft(@RequestBody Aircraft aircraft) {
        return aircraftRepository.save(aircraft);
    }

    /**
     *
     * Fügt mehrere Flugzeuge Hinzu und speicher diese dann in der liste
     * @param aircraftList
     * @return
     */
    @PostMapping("/addAll")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Aircraft> createMultipleAircraft(@RequestBody List<Aircraft> aircraftList) {
        return aircraftRepository.saveAll(aircraftList);
    }
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteAircraft(@PathVariable Long id) {

        if (!aircraftRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Aircraft not found");
        }

        if (!flightRepository.findByAircraft_Id(id).isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Aircraft is still assigned to flights");
        }

        aircraftRepository.deleteById(id);

        return ResponseEntity.ok("Aircraft deleted");
    }

}
