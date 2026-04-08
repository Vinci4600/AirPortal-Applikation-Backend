package org.example.projektarbeit_modul295_vincent_diergardt.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class FlightDTO {
    private Long id;
    private String flightNumber;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private Long aircraftId;
    private Long departureAirportId;
    private Long arrivalAirportId;
}
