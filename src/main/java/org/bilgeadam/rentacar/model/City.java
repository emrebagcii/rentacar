package org.bilgeadam.rentacar.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="city")
@Getter
@Setter
public class City {

    @Id
    private Long id;
    @Column(name = "city_name")
    private String cityName;
}
