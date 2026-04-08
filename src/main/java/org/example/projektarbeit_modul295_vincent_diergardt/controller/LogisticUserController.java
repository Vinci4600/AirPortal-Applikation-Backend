package org.example.projektarbeit_modul295_vincent_diergardt.controller;

import org.example.projektarbeit_modul295_vincent_diergardt.model.LogisticUser;
import org.example.projektarbeit_modul295_vincent_diergardt.repository.LogisticUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Der Type Logistic user controller.
 */
@RestController
@RequestMapping("/api/logisticUser")
@CrossOrigin(origins = "http://localhost:5173")
public class LogisticUserController {

    private final LogisticUserRepository repository;

    /**
     * Erstellt einen neuen Logistic user controller.
     *
     * @param repository the repository
     */
    public LogisticUserController(LogisticUserRepository repository) {
        this.repository = repository;
    }

    /**
     * Gibt alle Logistic User aus der Liste(Datenbank) an.
     *
     * @return the all logistic users
     */
    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public List<LogisticUser> getAllLogisticUsers() {
        return repository.findAll();
    }

    /**
     * Erstellt neue Logistic Users
     *
     * @param logisticUser the logistic user
     * @return the logistic user
     */
    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public LogisticUser createLogisticUser(@RequestBody LogisticUser logisticUser) {
        return repository.save(logisticUser);
    }//neuer Logistic User
    /**
     * Löscht einen Logistic User anhand der ID
     *
     * @param id die ID des Logistic Users
     */
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteLogisticUser(@PathVariable Long id) {

        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.ok("Logistic User wurde erfolgreich gelöscht.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Logistic User mit ID " + id + " wurde nicht gefunden.");
        }
    }



}
