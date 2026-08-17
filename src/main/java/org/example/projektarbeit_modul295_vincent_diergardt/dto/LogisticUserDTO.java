package org.example.projektarbeit_modul295_vincent_diergardt.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record LogisticUserDTO(
    Long id,

    @NotBlank(message = "Vorname ist erforderlich")
    @Size(max = 100, message = "Vorname darf maximal 100 Zeichen haben")
    String firstname,

    @NotBlank(message = "Nachname ist erforderlich")
    @Size(max = 100, message = "Nachname darf maximal 100 Zeichen haben")
    String lastname,

    @NotBlank(message = "Email ist erforderlich")
    @Email(message = "Email muss ein gültiges Format haben")
    @Size(max = 150, message = "Email darf maximal 150 Zeichen haben")
    String email,

    LocalDateTime createDate
) {}
