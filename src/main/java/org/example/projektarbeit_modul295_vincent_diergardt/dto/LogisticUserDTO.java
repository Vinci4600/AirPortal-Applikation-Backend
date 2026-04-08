package org.example.projektarbeit_modul295_vincent_diergardt.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class LogisticUserDTO {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private LocalDateTime createDate;
}
