package org.bilgeadam.rentacar.repository;

import org.bilgeadam.rentacar.model.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<District,Integer> {
    List<District>findByCityId(Long cityId);
}
