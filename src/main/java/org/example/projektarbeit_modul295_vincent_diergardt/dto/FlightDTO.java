package org.example.projektarbeit_modul295_vincent_diergardt.dto;

import java.time.LocalDateTime;

public record FlightDTO(
    Long id,
    String flightNumber,
    LocalDateTime departureTime,
    LocalDateTime arrivalTime,
    Long aircraftId,
    Long departureAirportId,
    Long arrivalAirportId
) {}
