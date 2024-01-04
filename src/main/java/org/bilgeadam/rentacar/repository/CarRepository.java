package org.bilgeadam.rentacar.repository;

import org.bilgeadam.rentacar.enums.FuelType;
import org.bilgeadam.rentacar.enums.Transmission;
import org.bilgeadam.rentacar.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    boolean existsByBrandAndModelAndColorAndTransmissionAndFuelType(String brand, String model, String color, Transmission transmission, FuelType fuelType);

    Car findByBrandAndModelAndColorAndTransmissionAndFuelType(String brand, String model, String color, Transmission transmission, FuelType fuelType);

    @Query("UPDATE Car c set c.isActive= false where c.id= :id")
    @Modifying
    void softDeleteCarById(@Param("id") Long id);





}
