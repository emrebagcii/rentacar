package org.bilgeadam.rentacar.repository;

import org.bilgeadam.rentacar.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {

}
