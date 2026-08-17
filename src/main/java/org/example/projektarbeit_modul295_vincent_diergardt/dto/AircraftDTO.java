package org.example.projektarbeit_modul295_vincent_diergardt.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record AircraftDTO(
    Long id,

    @NotBlank(message = "Modell ist erforderlich")
    @Size(max = 100, message = "Modell darf maximal 100 Zeichen haben")
    String model,

    @NotBlank(message = "Hersteller ist erforderlich")
    @Size(max = 100, message = "Hersteller darf maximal 100 Zeichen haben")
    String manufacture,

    @NotBlank(message = "Gewicht ist erforderlich")
    @Size(max = 50, message = "Gewicht darf maximal 50 Zeichen haben")
    String gewicht,

    LocalDateTime createDate
) {}
