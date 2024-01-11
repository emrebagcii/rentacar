package org.bilgeadam.rentacar.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name="car_detail")
@Getter
@Setter
public class CarDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="car_id")
    private Long carId;

    private Long kilometer;

    @Column(name="car_plate")
    private String carPlate;

    @Column(name = "is_renting")
    private boolean isRenting;

    @Column(name="is_active")
    private boolean isActive;

    @Column(name="created_date")
    private LocalDateTime createdDate;

    @Column(name="updated_date")
    private LocalDateTime updatedDate;

}
