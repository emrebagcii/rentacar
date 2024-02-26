package org.bilgeadam.rentacar.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class CarListDto {

    private String categoryName;

    private String brand;

    private String model;

    private String color;

    private Integer year;

    @Enumerated(EnumType.STRING)
    private Transmission transmission;

    @Enumerated(EnumType.STRING)
    private FuelType fuelType;

    private double rentPrice;

    private Integer availableCar;

    private boolean isActive;
}
