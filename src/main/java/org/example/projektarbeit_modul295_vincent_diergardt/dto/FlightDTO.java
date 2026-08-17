package org.example.projektarbeit_modul295_vincent_diergardt.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record FlightDTO(
    Long id,

    @NotBlank(message = "Flugnummer ist erforderlich")
    @Size(max = 10, message = "Flugnummer darf maximal 10 Zeichen haben")
    String flightNumber,

    @NotNull(message = "Abflugzeit ist erforderlich")
    LocalDateTime departureTime,

    @NotNull(message = "Ankunftszeit ist erforderlich")
    LocalDateTime arrivalTime,

    @NotNull(message = "Flugzeug ist erforderlich")
    Long aircraftId,

    @NotNull(message = "Abflughafen ist erforderlich")
    Long departureAirportId,

    @NotNull(message = "Zielflughafen ist erforderlich")
    Long arrivalAirportId
) {}
