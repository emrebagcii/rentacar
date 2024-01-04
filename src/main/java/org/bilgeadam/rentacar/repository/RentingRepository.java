package org.bilgeadam.rentacar.repository;

import org.bilgeadam.rentacar.model.Renting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentingRepository extends JpaRepository<Renting, Long> {
}
