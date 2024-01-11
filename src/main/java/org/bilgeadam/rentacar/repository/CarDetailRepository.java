package org.bilgeadam.rentacar.repository;

import org.bilgeadam.rentacar.model.CarDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarDetailRepository extends JpaRepository<CarDetail,Long> {

    boolean existsByCarPlate(String carPlate);
}
