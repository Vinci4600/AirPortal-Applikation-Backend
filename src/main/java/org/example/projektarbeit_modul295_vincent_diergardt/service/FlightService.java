package org.example.projektarbeit_modul295_vincent_diergardt.service;

import org.example.projektarbeit_modul295_vincent_diergardt.dto.FlightDTO;
import org.example.projektarbeit_modul295_vincent_diergardt.model.Flight;
import org.example.projektarbeit_modul295_vincent_diergardt.repository.AircraftRepository;
import org.example.projektarbeit_modul295_vincent_diergardt.repository.AirportRepository;
import org.example.projektarbeit_modul295_vincent_diergardt.repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
                .toList();
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
        return new FlightDTO(
                flight.getFlight_id(),
                flight.getFlightNumber(),
                flight.getDepartureTime(),
                flight.getArrivalTime(),
                flight.getAircraft() != null ? flight.getAircraft().getId() : null,
                flight.getDepartureAirport() != null ? flight.getDepartureAirport().getId() : null,
                flight.getArrivalAirport() != null ? flight.getArrivalAirport().getId() : null
        );
    }

    private Flight convertToEntity(FlightDTO dto) {
        Flight flight = new Flight();
        flight.setFlightNumber(dto.flightNumber());
        flight.setDepartureTime(dto.departureTime());
        flight.setArrivalTime(dto.arrivalTime());
        
        if (dto.aircraftId() != null) {
            aircraftRepository.findById(dto.aircraftId()).ifPresent(flight::setAircraft);
        }
        if (dto.departureAirportId() != null) {
            airportRepository.findById(dto.departureAirportId()).ifPresent(flight::setDepartureAirport);
        }
        if (dto.arrivalAirportId() != null) {
            airportRepository.findById(dto.arrivalAirportId()).ifPresent(flight::setArrivalAirport);
        }
        
        return flight;
    }
}
