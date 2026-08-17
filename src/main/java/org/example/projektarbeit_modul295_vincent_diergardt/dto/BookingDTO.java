package org.example.projektarbeit_modul295_vincent_diergardt.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record BookingDTO(
    Long id,

    @NotNull(message = "Flug ist erforderlich")
    Long flightId,

    @NotNull(message = "Passagier ist erforderlich")
    Long passengerId,

    LocalDateTime bookingDate
) {}
