package org.bilgeadam.rentacar.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name="users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="nationality_number")
    private String nationalityNumber;

    @Column(name = "driver_licence_no")
    private String driverLicenceNo;

    @Column(name="driver_licence_type")
    private String driverLicenceType;

    @Column(name="email")
    private String email;

    @Column(name = "city_id")
    private Integer cityId;

    @Column(name = "district_id")
    private Integer districtId;

    @Column(name="address_line")
    private String addressLine;

    @Column(name = "birth_date")
    private LocalDateTime birthDate;

    @Column(name="password")
    private String password;

    @Column(name = "roles")
    private String roles;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;
}
