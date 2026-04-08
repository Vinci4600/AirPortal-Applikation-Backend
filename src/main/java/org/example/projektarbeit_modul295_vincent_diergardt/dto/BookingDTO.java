package org.example.projektarbeit_modul295_vincent_diergardt.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class BookingDTO {
    private Long id;
    private Long flightId;
    private Long passengerId;
    private LocalDateTime bookingDate;
}
