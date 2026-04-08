package org.example.projektarbeit_modul295_vincent_diergardt.security;

import org.example.projektarbeit_modul295_vincent_diergardt.service.JwtService;
import org.springframework.security.core.userdetails.UserDetailsService;

public class JwtAuthenticationFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
}
