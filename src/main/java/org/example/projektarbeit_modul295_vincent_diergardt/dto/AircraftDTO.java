package org.example.projektarbeit_modul295_vincent_diergardt.dto;

import java.time.LocalDateTime;

public record AircraftDTO(
    Long id,
    String model,
    String manufacture,
    String gewicht,
    LocalDateTime createDate
) {}
