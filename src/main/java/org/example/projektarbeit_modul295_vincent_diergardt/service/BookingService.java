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
        return new BookingDTO(
                booking.getId(),
                booking.getFlight() != null ? booking.getFlight().getFlight_id() : null,
                booking.getPassenger() != null ? booking.getPassenger().getId() : null,
                booking.getBookingDate()
        );
    }

    private Booking convertToEntity(BookingDTO dto) {
        Booking booking = new Booking();
        booking.setBookingDate(dto.bookingDate());
        
        if (dto.flightId() != null) {
            flightRepository.findById(dto.flightId()).ifPresent(booking::setFlight);
        }
        if (dto.passengerId() != null) {
            passengerRepository.findById(dto.passengerId()).ifPresent(booking::setPassenger);
        }
        
        return booking;
    }
}
