package org.example.projektarbeit_modul295_vincent_diergardt.controller;

import org.example.projektarbeit_modul295_vincent_diergardt.dto.LogisticUserDTO;
import org.example.projektarbeit_modul295_vincent_diergardt.service.LogisticUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Der Type Logistic user controller.
 */
@RestController
@RequestMapping("/api/logistic_users")
@CrossOrigin(origins = "http://localhost:5173")
public class LogisticUserController {
    private final LogisticUserService logisticUserService;

    /**
     * Erstellt ein neues Logistic User Controller.
     *
     * @param logisticUserService the logistic user service
     */
    public LogisticUserController(LogisticUserService logisticUserService) {
        this.logisticUserService = logisticUserService;
    }

    /**
     * Gibt alle LogisticUsers an
     *
     * @return the all logistic users
     */
    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public List<LogisticUserDTO> getAllLogisticUsers() {
        return logisticUserService.getAllLogisticUsers();
    }

    /**
     * Erstellt neue LogisticUsers
     *
     * @param logisticUserDTO the logistic user dto
     * @return the logistic user dto
     */
    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public LogisticUserDTO createLogisticUser(@RequestBody LogisticUserDTO logisticUserDTO) {
        return logisticUserService.createLogisticUser(logisticUserDTO);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteLogisticUser(@PathVariable Long id) {

        if (!logisticUserService.existsById(id)) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Logistic user not found");
        }

        logisticUserService.deleteLogisticUser(id);

        return ResponseEntity.ok("Logistic user deleted successfully");
    }
}
