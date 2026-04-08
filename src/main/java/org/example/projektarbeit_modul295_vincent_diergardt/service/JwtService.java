package org.example.projektarbeit_modul295_vincent_diergardt.service;

import org.springframework.beans.factory.annotation.Value;

public class JwtService {
    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long expirationTime;
}
