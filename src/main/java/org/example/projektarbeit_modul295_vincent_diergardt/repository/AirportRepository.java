package org.example.projektarbeit_modul295_vincent_diergardt.repository;

import org.example.projektarbeit_modul295_vincent_diergardt.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * The interface Airport repository.
 */
@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {
}
