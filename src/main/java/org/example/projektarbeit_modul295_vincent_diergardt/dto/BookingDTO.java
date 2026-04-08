package org.example.projektarbeit_modul295_vincent_diergardt.dto;

import java.time.LocalDateTime;

public record BookingDTO(
    Long id,
    Long flightId,
    Long passengerId,
    LocalDateTime bookingDate
) {}
