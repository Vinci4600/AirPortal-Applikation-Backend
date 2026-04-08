package org.example.projektarbeit_modul295_vincent_diergardt.controller;

import org.example.projektarbeit_modul295_vincent_diergardt.dto.PassengerDTO;
import org.example.projektarbeit_modul295_vincent_diergardt.service.PassengerService;
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
    private final PassengerService passengerService;

    /**
     * Erstellt ein neues Passenger Controller.
     *
     * @param passengerService the passenger service
     */
    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    /**
     * Gibt alle Passengers an
     *
     * @return the all passengers
     */
    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public List<PassengerDTO> getAllPassengers() {
        return passengerService.getAllPassengers();
    }

    /**
     * Erstellt neue Passengers
     *
     * @param passengerDTO the passenger dto
     * @return the passenger dto
     */
    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public PassengerDTO createPassenger(@RequestBody PassengerDTO passengerDTO) {
        return passengerService.createPassenger(passengerDTO);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deletePassenger(@PathVariable Long id) {

        if (!passengerService.existsById(id)) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Passenger not found");
        }

        passengerService.deletePassenger(id);

        return ResponseEntity.ok("Passenger deleted successfully");
    }
}
