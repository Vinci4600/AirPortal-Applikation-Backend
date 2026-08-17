package org.example.projektarbeit_modul295_vincent_diergardt.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AirportDTO(
    Long id,

    @NotBlank(message = "Name ist erforderlich")
    @Size(max = 100, message = "Name darf maximal 100 Zeichen haben")
    String name,

    @NotBlank(message = "Land ist erforderlich")
    @Size(max = 100, message = "Land darf maximal 100 Zeichen haben")
    String country,

    @NotBlank(message = "IATA Code ist erforderlich")
    @Size(min = 3, max = 3, message = "IATA Code muss genau 3 Zeichen haben")
    String iataCode
) {}
