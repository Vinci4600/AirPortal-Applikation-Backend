package org.example.projektarbeit_modul295_vincent_diergardt.controller;

import org.example.projektarbeit_modul295_vincent_diergardt.model.Airport;
import org.example.projektarbeit_modul295_vincent_diergardt.repository.AirportRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Der Type Airport controller.
 */
@RestController
@RequestMapping("/api/airports")
@CrossOrigin(origins = "http://localhost:5173")
public class AirportController {

    private final AirportRepository repository;

    /**
     * Erstellt einen neuen Airport controller.
     *
     * @param repository the repository
     */
    public AirportController(AirportRepository repository) {
        this.repository = repository;
    }

    /**
     * Gibt alle Flughafen zurück aus der datenbank
     *
     * @return the all
     */
    @GetMapping("/all")
    public List<Airport> getAll() {
        return repository.findAll();
    }

    /**
     * Erstellt einen neuen Flughafen und fügt diese in die Liste ein
     *
     *
     * @return the airport
     */
    @PostMapping("/add")
    public Airport createAirport(@RequestBody Airport airport) {
        return repository.save(airport);
    }

    /**
     *
     * Erstellt mehrere Flughäfen und fügt diese in die Liste hinzu.
     */
    @PostMapping("/addAll")
    public List<Airport> createAirports(@RequestBody List<Airport> airports) {
        return repository.saveAll(airports);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAirport(@PathVariable Long id) {

        // Prüfen ob Airport existiert
        if (!repository.existsById(id)) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Airport not found");
        }

        try {
            repository.deleteById(id);
            return ResponseEntity.ok("Airport deleted successfully");

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Airport kann nicht gelöscht werden, da noch Flights existieren!");
        }
    }}


