package org.bilgeadam.rentacar.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.bilgeadam.rentacar.enums.FuelType;
import org.bilgeadam.rentacar.enums.Transmission;

import java.time.LocalDateTime;

@Entity
@Table(name="car")
@Getter
@Setter
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "category_id")
    private Long categoryId;

    private String brand;

    private String model;

    private String color;

    @Enumerated(EnumType.STRING)
    @Column(name = "fuel_type")
    private FuelType fuelType;

    @Enumerated(EnumType.STRING)
    private Transmission transmission;

    private Integer year;

    private String image;

    @Column(name="available_car")
    private Integer availableCar;

    @Column(name = "rent_price")
    private Double rentPrice;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "updated_name")
    private LocalDateTime updatedDate;
}