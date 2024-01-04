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

    @Column(name="category_id")
    private Long categoryId;

    private String brand;

    private String model;

    private Integer year;

    @Enumerated(EnumType.STRING)
    private Transmission transmission;

    @Enumerated(EnumType.STRING)
    @Column(name="fuel_type")
    private FuelType fuelType;

    private String color;

    private Integer kilometer;

    @Column(name="car_plate")
    private String carPlate;

    @Column(name="rent_price")
    private double rentPrice;

    @Column(name="image_url")
    private String image;

    @Column(name="is_renting")
    private boolean isRenting;

    @Column(name="is_active")
    private boolean isActive;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

}
