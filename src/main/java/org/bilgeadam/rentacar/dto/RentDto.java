package org.bilgeadam.rentacar.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bilgeadam.rentacar.enums.FuelType;
import org.bilgeadam.rentacar.enums.Transmission;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RentDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String rentingCity;

    private String deliveryCity;

    private Long carId;

    private String brand;

    private String model;

    private Transmission transmission;

    private FuelType fuelType;

    private String carPlate;

    private String color;

    private LocalDateTime rentingDate;

    private LocalDateTime deliveryDate;

    private Integer rentingDay;

}
