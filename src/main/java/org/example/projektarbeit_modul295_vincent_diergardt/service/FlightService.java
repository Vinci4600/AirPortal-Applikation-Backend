package org.example.projektarbeit_modul295_vincent_diergardt.service;

import org.example.projektarbeit_modul295_vincent_diergardt.dto.FlightDTO;
import org.example.projektarbeit_modul295_vincent_diergardt.model.Flight;
import org.example.projektarbeit_modul295_vincent_diergardt.repository.AircraftRepository;
import org.example.projektarbeit_modul295_vincent_diergardt.repository.AirportRepository;
import org.example.projektarbeit_modul295_vincent_diergardt.repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightService {

    private final FlightRepository flightRepository;
    private final AircraftRepository aircraftRepository;
    private final AirportRepository airportRepository;

    public FlightService(FlightRepository flightRepository, AircraftRepository aircraftRepository, AirportRepository airportRepository) {
        this.flightRepository = flightRepository;
        this.aircraftRepository = aircraftRepository;
        this.airportRepository = airportRepository;
    }

    public List<FlightDTO> getAllFlights() {
        return flightRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public FlightDTO createFlight(FlightDTO flightDTO) {
        Flight flight = convertToEntity(flightDTO);
        Flight savedFlight = flightRepository.save(flight);
        return convertToDTO(savedFlight);
    }

    public void deleteFlight(Long id) {
        flightRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return flightRepository.existsById(id);
    }

    private FlightDTO convertToDTO(Flight flight) {
        FlightDTO dto = new FlightDTO();
        dto.setId(flight.getFlight_id());
        dto.setFlightNumber(flight.getFlightNumber());
        dto.setDepartureTime(flight.getDepartureTime());
        dto.setArrivalTime(flight.getArrivalTime());
        if (flight.getAircraft() != null) {
            dto.setAircraftId(flight.getAircraft().getId());
        }
        if (flight.getDepartureAirport() != null) {
            dto.setDepartureAirportId(flight.getDepartureAirport().getId());
        }
        if (flight.getArrivalAirport() != null) {
            dto.setArrivalAirportId(flight.getArrivalAirport().getId());
        }
        return dto;
    }

    private Flight convertToEntity(FlightDTO dto) {
        Flight flight = new Flight();
        flight.setFlightNumber(dto.getFlightNumber());
        flight.setDepartureTime(dto.getDepartureTime());
        flight.setArrivalTime(dto.getArrivalTime());
        
        if (dto.getAircraftId() != null) {
            aircraftRepository.findById(dto.getAircraftId()).ifPresent(flight::setAircraft);
        }
        if (dto.getDepartureAirportId() != null) {
            airportRepository.findById(dto.getDepartureAirportId()).ifPresent(flight::setDepartureAirport);
        }
        if (dto.getArrivalAirportId() != null) {
            airportRepository.findById(dto.getArrivalAirportId()).ifPresent(flight::setArrivalAirport);
        }
        
        return flight;
    }
}
