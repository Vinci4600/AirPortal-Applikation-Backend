package org.example.projektarbeit_modul295_vincent_diergardt.repository;

import org.example.projektarbeit_modul295_vincent_diergardt.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * The interface Passenger repository.
 */
@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {
}

