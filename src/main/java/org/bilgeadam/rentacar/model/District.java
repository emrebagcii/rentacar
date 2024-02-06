package org.bilgeadam.rentacar.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="district")
@Getter
@Setter
public class District {
    
    @Id
    private Long id;
    @Column(name="district_name")
    private String districtName;
    @Column(name="city_id")
    private Long cityId;
}
