package org.example.projektarbeit_modul295_vincent_diergardt.dto;

import java.time.LocalDateTime;

public record LogisticUserDTO(
    Long id,
    String firstname,
    String lastname,
    String email,
    LocalDateTime createDate
) {}
