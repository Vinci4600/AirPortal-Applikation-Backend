package org.example.projektarbeit_modul295_vincent_diergardt.repository;

import org.example.projektarbeit_modul295_vincent_diergardt.model.LogisticUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Logistic user repository.
 */
@Repository
public interface LogisticUserRepository extends JpaRepository<LogisticUser, Long> {
}