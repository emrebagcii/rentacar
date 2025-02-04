package org.bilgeadam.rentacar.repository;

import org.bilgeadam.rentacar.dto.CarDetailDto;
import org.bilgeadam.rentacar.model.CarDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarDetailRepository extends JpaRepository<CarDetail, Long> {

    boolean existsByCarPlate(String carPlate);

    Integer countByCarIdAndIsRentingFalse(Long carId);

    @Query("SELECT c.id FROM CarDetail c WHERE c.carId= :carId And c.isActive=true And c.isRenting=false ORDER BY c.kilometer desc LIMIT 1")
    Long findRentingByCarIdAndIsActiveTrueAndIsRentingFalse(@Param("carId") Long carId);

    @Query("UPDATE CarDetail c set c.isRenting= true WHERE c.id= :id ")
    @Modifying
    void updateIsRentingById(@Param("id") Long id);

    @Query("select new org.bilgeadam.rentacar.dto.CarDetailDto(cd.id , cd.carId  ,cc.categoryName, c.brand, c.model, c.year, cd.color, c.transmission, c.fuelType, cd.carPlate, cd.isActive, cd.isRenting) \n" +
            "from Car c\n" +
            "inner join CarDetail cd on cd.carId=c.id\n" +
            "inner join CarCategory cc on cc.id=c.categoryId")
    @Modifying
    List<CarDetailDto> getInventoryList();

    @Query("UPDATE CarDetail c set c.isRenting= false WHERE c.id= :id ")
    @Modifying
    void endRentingCar(@Param("id") Long id);
}
