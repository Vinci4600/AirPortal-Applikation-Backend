package org.example.projektarbeit_modul295_vincent_diergardt.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AircraftDTO {
    private Long id;
    private String model;
    private String manufacture;
    private String gewicht;
    private LocalDateTime createDate;
}
