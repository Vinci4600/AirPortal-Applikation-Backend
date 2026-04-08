package org.example.projektarbeit_modul295_vincent_diergardt.service;

import org.example.projektarbeit_modul295_vincent_diergardt.dto.AircraftDTO;
import org.example.projektarbeit_modul295_vincent_diergardt.model.Aircraft;
import org.example.projektarbeit_modul295_vincent_diergardt.repository.AircraftRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AircraftService {

    private final AircraftRepository aircraftRepository;

    public AircraftService(AircraftRepository aircraftRepository) {
        this.aircraftRepository = aircraftRepository;
    }

    public List<AircraftDTO> getAllAircrafts() {
        return aircraftRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public AircraftDTO createAircraft(AircraftDTO aircraftDTO) {
        Aircraft aircraft = convertToEntity(aircraftDTO);
        Aircraft savedAircraft = aircraftRepository.save(aircraft);
        return convertToDTO(savedAircraft);
    }

    public void deleteAircraft(Long id) {
        aircraftRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return aircraftRepository.existsById(id);
    }

    private AircraftDTO convertToDTO(Aircraft aircraft) {
        AircraftDTO dto = new AircraftDTO();
        dto.setId(aircraft.getId());
        dto.setModel(aircraft.getModel());
        dto.setManufacture(aircraft.getManufacture());
        dto.setGewicht(aircraft.getGewicht());
        dto.setCreateDate(aircraft.getCreateDate());
        return dto;
    }

    private Aircraft convertToEntity(AircraftDTO dto) {
        Aircraft aircraft = new Aircraft();
        aircraft.setModel(dto.getModel());
        aircraft.setManufacture(dto.getManufacture());
        aircraft.setGewicht(dto.getGewicht());
        return aircraft;
    }
}
