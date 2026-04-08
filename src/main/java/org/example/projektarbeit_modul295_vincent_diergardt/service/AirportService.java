package org.example.projektarbeit_modul295_vincent_diergardt.service;

import org.example.projektarbeit_modul295_vincent_diergardt.dto.AirportDTO;
import org.example.projektarbeit_modul295_vincent_diergardt.model.Airport;
import org.example.projektarbeit_modul295_vincent_diergardt.repository.AirportRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AirportService {

    private final AirportRepository airportRepository;

    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    public List<AirportDTO> getAllAirports() {
        return airportRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public AirportDTO createAirport(AirportDTO airportDTO) {
        Airport airport = convertToEntity(airportDTO);
        Airport savedAirport = airportRepository.save(airport);
        return convertToDTO(savedAirport);
    }

    public boolean existsById(Long id) {
        return airportRepository.existsById(id);
    }

    public void deleteAirport(Long id) {
        airportRepository.deleteById(id);
    }

    private AirportDTO convertToDTO(Airport airport) {
        AirportDTO dto = new AirportDTO();
        dto.setId(airport.getId());
        dto.setName(airport.getName());
        dto.setCountry(airport.getCountry());
        dto.setIataCode(airport.getIataCode());
        return dto;
    }

    private Airport convertToEntity(AirportDTO dto) {
        Airport airport = new Airport();
        airport.setName(dto.getName());
        airport.setCountry(dto.getCountry());
        airport.setIataCode(dto.getIataCode());
        return airport;
    }
}
