package org.example.projektarbeit_modul295_vincent_diergardt.service;

import org.example.projektarbeit_modul295_vincent_diergardt.dto.PassengerDTO;
import org.example.projektarbeit_modul295_vincent_diergardt.model.Passenger;
import org.example.projektarbeit_modul295_vincent_diergardt.repository.PassengerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PassengerService {

    private final PassengerRepository passengerRepository;

    public PassengerService(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    public List<PassengerDTO> getAllPassengers() {
        return passengerRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public PassengerDTO createPassenger(PassengerDTO passengerDTO) {
        Passenger passenger = convertToEntity(passengerDTO);
        Passenger savedPassenger = passengerRepository.save(passenger);
        return convertToDTO(savedPassenger);
    }

    public void deletePassenger(Long id) {
        passengerRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return passengerRepository.existsById(id);
    }

    private PassengerDTO convertToDTO(Passenger passenger) {
        PassengerDTO dto = new PassengerDTO();
        dto.setId(passenger.getId());
        dto.setFirstname(passenger.getFirstname());
        dto.setLastname(passenger.getLastname());
        dto.setEmail(passenger.getEmail());
        return dto;
    }

    private Passenger convertToEntity(PassengerDTO dto) {
        Passenger passenger = new Passenger();
        passenger.setFirstname(dto.getFirstname());
        passenger.setLastname(dto.getLastname());
        passenger.setEmail(dto.getEmail());
        return passenger;
    }
}
