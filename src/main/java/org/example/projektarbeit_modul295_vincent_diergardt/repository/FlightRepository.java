package org.example.projektarbeit_modul295_vincent_diergardt.repository;

import org.example.projektarbeit_modul295_vincent_diergardt.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * The interface Flight repository.
 */
@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByAircraft_Id(Long aircraftId);
}

