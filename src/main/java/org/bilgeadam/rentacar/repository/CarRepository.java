package org.bilgeadam.rentacar.repository;

import org.bilgeadam.rentacar.dto.CarListDto;
import org.bilgeadam.rentacar.enums.FuelType;
import org.bilgeadam.rentacar.enums.Transmission;
import org.bilgeadam.rentacar.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    boolean existsByBrandAndModelAndTransmissionAndFuelTypeAndYear(String brand, String model,  Transmission transmission, FuelType fuelType, Integer year);

    Car findByBrandAndModelAndTransmissionAndFuelType(String brand, String model, Transmission transmission, FuelType fuelType);

    @Query("UPDATE Car c set c.isActive= false where c.id= :id")
    @Modifying
    void softDeleteCarById(@Param("id") Long id);

    @Query("UPDATE Car c set c.availableCar = :countCar where c.id = :id")
    @Modifying
    void updateAvailableCarById(@Param("id") Long id, @Param("countCar") Integer countCar);

    @Query("UPDATE Car c set c.availableCar = c.availableCar-1 where c.id = :id")
    @Modifying
    void updateRentingAvailableCarById(@Param("id") Long id);

    @Query("select new org.bilgeadam.rentacar.dto.CarListDto(c.id,c.categoryId,cc.categoryName ,c.brand ,c.model ,c.year ,c.transmission ,c.fuelType ,c.rentPrice ,c.availableCar ,c.isActive)from Car c inner join CarCategory cc on cc.id = c.categoryId")
    List<CarListDto> getAllCarListWithCategory();
}
