package org.bilgeadam.rentacar.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bilgeadam.rentacar.enums.FuelType;
import org.bilgeadam.rentacar.enums.Transmission;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarDetailDto {

    private Long id;

    private Long carId;

    private String categoryName;

    private String brand;

    private String model;

    private Integer year;

    private String color;

    private Transmission transmission;

    private FuelType fuelType;

    private String carPlate;

    private boolean isActive;

    private boolean isRenting;

}
