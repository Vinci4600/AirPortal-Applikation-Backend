package org.example.projektarbeit_modul295_vincent_diergardt.repository;

import org.example.projektarbeit_modul295_vincent_diergardt.model.Aircraft;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * The interface Aircraft repository.
 */
@Repository
public interface AircraftRepository extends JpaRepository<Aircraft, Long> {
}

