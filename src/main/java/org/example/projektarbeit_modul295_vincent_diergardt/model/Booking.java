package org.example.projektarbeit_modul295_vincent_diergardt.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * Der type Booking.
 */
@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    /**
     * Mehrer zu einer Verbindung
     */
    @ManyToOne
    @JoinColumn(name = "flight_id")
    @JsonIgnore
    private Flight flight;

    @ManyToOne
    @JoinColumn(name = "passenger_id")
    @JsonIgnore // Markiert die Rückreferenz
    private Passenger passenger;


    @Column(name = "booking_date")
    private LocalDateTime bookingDate;

    /** Getter und Setter
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets flight.
     *
     * @return the flight
     */
    public Flight getFlight() {
        return flight;
    }

    /**
     * Sets flight.
     *
     * @param flight the flight
     */
    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    /**
     * Gets passenger.
     *
     * @return the passenger
     */
    public Passenger getPassenger() {
        return passenger;
    }

    /**
     * Sets passenger.
     *
     * @param passenger the passenger
     */
    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    /**
     * Gets booking date.
     *
     * @return the booking date
     */
    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    /**
     * Sets booking date.
     *
     * @param bookingDate the booking date
     */
    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }
}
