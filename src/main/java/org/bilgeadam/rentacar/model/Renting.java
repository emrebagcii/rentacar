package org.bilgeadam.rentacar.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "renting")
@Getter
@Setter
public class Renting {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="car_id")
    private Long carId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "driver_licence_type")
    private String driverLicenceType;

    @Column(name = "driver_licence_no")
    private String driverLicenceNo;

    @Column(name="rent_city_id")
    private Long rentCityId;

    @Column(name="delivery_city_id")
    private Long deliveryCityId;

    @Column(name="renting_date")
    private LocalDateTime rentingDate;

    @Column(name="delivery_date")
    private LocalDateTime deliveryDate;

    @Column(name="renting_day")
    private Integer rentingDay;

    @Column(name="renting_amount")
    private Double rentingAmount;

    @Column(name="created_date")
    private LocalDateTime createdDate;

    @Column(name="renting_state")
    private boolean rentingState;

}
