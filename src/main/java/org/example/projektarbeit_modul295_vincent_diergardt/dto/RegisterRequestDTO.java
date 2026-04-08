package org.example.projektarbeit_modul295_vincent_diergardt.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequestDTO(
    @NotBlank(message = "Username ist erforderlich")
    @Size(min = 3, max = 50, message = "Username muss zwischen 3 und 50 Zeichen haben")
    String username,

    @NotBlank(message = "Email ist erforderlich")
    @Email(message = "Email muss ein gültiges Format haben")
    @Size(max = 100, message = "Email darf maximal 100 Zeichen haben")
    String email,

    @NotBlank(message = "Passwort ist erforderlich")
    @Size(min = 6, message = "Passwort muss mindestens 6 Zeichen haben")
    String password
) {
    @Override
    public String toString() {
        return "RegisterRequestDTO{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
