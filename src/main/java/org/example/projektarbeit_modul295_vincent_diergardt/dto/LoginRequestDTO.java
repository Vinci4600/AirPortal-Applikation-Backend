package org.example.projektarbeit_modul295_vincent_diergardt.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(
    @NotBlank(message = "Username oder Email ist erforderlich")
    String usernameOrEmail,

    @NotBlank(message = "Passwort ist erforderlich")
    String password
) {}
