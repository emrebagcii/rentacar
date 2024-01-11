package org.bilgeadam.rentacar.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RentingRequest {

    private Long carId;

    private Long userId;

    private String driverLicenceType;

    private String driverLicenceNo;

    private Long rentCityId;

    private Long deliveryCityId;

    private LocalDateTime rentingDate;

    private LocalDateTime deliveryDate;

    private Integer rentingDay;

    private LocalDateTime createdDate;

}


