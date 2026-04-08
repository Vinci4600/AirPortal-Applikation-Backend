package org.example.projektarbeit_modul295_vincent_diergardt.dto;

public record LoginResponseDTO(
    String token,
    Long userId,
    String username,
    String email,
    String role,
    long expiresIn
) {
    private static final String TOKEN_TYPE = "Bearer";

    public String tokenType() {
        return TOKEN_TYPE;
    }

    @Override
    public String toString() {
        return "LoginResponseDTO{" +
                "token='[HIDDEN]'" +
                ", tokenType='" + TOKEN_TYPE + '\'' +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", expiresIn=" + expiresIn +
                '}';
    }
}
