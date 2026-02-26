package org.example.projektarbeit_modul295_vincent_diergardt.repository;

import org.example.projektarbeit_modul295_vincent_diergardt.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * The interface Booking repository.
 */
@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
}
