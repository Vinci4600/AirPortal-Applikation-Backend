package org.example.projektarbeit_modul295_vincent_diergardt.service;

import org.example.projektarbeit_modul295_vincent_diergardt.dto.BookingDTO;
import org.example.projektarbeit_modul295_vincent_diergardt.model.Booking;
import org.example.projektarbeit_modul295_vincent_diergardt.repository.BookingRepository;
import org.example.projektarbeit_modul295_vincent_diergardt.repository.FlightRepository;
import org.example.projektarbeit_modul295_vincent_diergardt.repository.PassengerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final FlightRepository flightRepository;
    private final PassengerRepository passengerRepository;

    public BookingService(BookingRepository bookingRepository, FlightRepository flightRepository, PassengerRepository passengerRepository) {
        this.bookingRepository = bookingRepository;
        this.flightRepository = flightRepository;
        this.passengerRepository = passengerRepository;
    }

    public List<BookingDTO> getAllBookings() {
        return bookingRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public BookingDTO createBooking(BookingDTO bookingDTO) {
        Booking booking = convertToEntity(bookingDTO);
        Booking savedBooking = bookingRepository.save(booking);
        return convertToDTO(savedBooking);
    }

    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return bookingRepository.existsById(id);
    }

    private BookingDTO convertToDTO(Booking booking) {
        BookingDTO dto = new BookingDTO();
        dto.setId(booking.getId());
        dto.setBookingDate(booking.getBookingDate());
        if (booking.getFlight() != null) {
            dto.setFlightId(booking.getFlight().getFlight_id());
        }
        if (booking.getPassenger() != null) {
            dto.setPassengerId(booking.getPassenger().getId());
        }
        return dto;
    }

    private Booking convertToEntity(BookingDTO dto) {
        Booking booking = new Booking();
        booking.setBookingDate(dto.getBookingDate());
        
        if (dto.getFlightId() != null) {
            flightRepository.findById(dto.getFlightId()).ifPresent(booking::setFlight);
        }
        if (dto.getPassengerId() != null) {
            passengerRepository.findById(dto.getPassengerId()).ifPresent(booking::setPassenger);
        }
        
        return booking;
    }
}
