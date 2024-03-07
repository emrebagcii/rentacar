package org.bilgeadam.rentacar.repository;

import org.bilgeadam.rentacar.dto.RentDto;
import org.bilgeadam.rentacar.model.Renting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RentingRepository extends JpaRepository<Renting, Long> {

    @Query("update Renting r set r.rentingState=false where r.id=:id")
    @Modifying
    void endRenting(@Param("id") Long id);

    @Query("select new org.bilgeadam.rentacar.dto.RentDto(r.id, u.firstName, u.lastName, ct.cityName as rentingCity, ct1.cityName as deliveryCity, cd.id, c.brand, c.model, c.transmission, c.fuelType, cd.carPlate, cd.color, r.rentingDate, r.deliveryDate, r.rentingDay) \n" +
            "from\n" +
            "    Renting r\n" +
            "inner join CarDetail cd on cd.id=r.carId\n" +
            "inner join Car c on c.id=cd.carId\n" +
            "inner join User u on u.id=r.userId\n" +
            "inner join City ct on ct.id=r.rentCityId\n" +
            "inner join City ct1 on ct1.id=r.deliveryCityId\n" +
            "where cd.id=:carId\n"+
            " and\n"+
            "r.rentingState=true")
    RentDto getRentingByCarIdAndRentingState(@Param(value = "carId") Long id);

}
