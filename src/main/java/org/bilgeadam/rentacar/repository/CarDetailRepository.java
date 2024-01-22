package org.bilgeadam.rentacar.repository;

import org.bilgeadam.rentacar.model.CarDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarDetailRepository extends JpaRepository<CarDetail,Long> {

    boolean existsByCarPlate(String carPlate);

    Integer countByCarIdAndIsRentingFalse(Long carId);

    @Query("SELECT c.id FROM CarDetail c WHERE c.carId= :carId And c.isActive=true And c.isRenting=false ORDER BY c.kilometer desc LIMIT 1")
    Long findRentingByCarIdAndIsActiveTrueAndIsRentingFalse(@Param("carId") Long carId);

    @Query("UPDATE CarDetail c set c.isRenting= true WHERE c.id= :id ")
    @Modifying
    void updateIsRentingById(@Param("id") Long id);
}
