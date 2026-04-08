package org.example.projektarbeit_modul295_vincent_diergardt.dto;

public record RegisterResponseDTO(
    Long id,
    String username,
    String email,
    String role,
    String message
) {
    public RegisterResponseDTO(Long id, String username, String email, String role) {
        this(id, username, email, role, "Registrierung erfolgreich! Willkommen " + username + "!");
    }
}
