package org.bilgeadam.rentacar.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.bilgeadam.rentacar.dto.RentingRequest;
import org.bilgeadam.rentacar.model.Car;
import org.bilgeadam.rentacar.model.Renting;
import org.bilgeadam.rentacar.model.UserHistory;
import org.bilgeadam.rentacar.repository.CarRepository;
import org.bilgeadam.rentacar.repository.RentingRepository;
import org.bilgeadam.rentacar.repository.UserHistoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class RentingService {

    private final RentingRepository rentingRepository;
    private final CarRepository carRepository;
    private final UserHistoryRepository historyRepository;

    //TODO: mapStruct kullan
    public void doRent(RentingRequest rentingRequest){

        Renting renting = new Renting();
        renting.setCarId(rentingRequest.getCarId());
        renting.setUserId(rentingRequest.getUserId());
        renting.setDriverLicenceType(rentingRequest.getDriverLicenceType());
        renting.setDriverLicenceNo(rentingRequest.getDriverLicenceNo());
        renting.setRentCityId(rentingRequest.getRentCityId());
        renting.setDeliveryCityId(rentingRequest.getDeliveryCityId());
        renting.setRentingDate(rentingRequest.getRentingDate());
        renting.setDeliveryDate(rentingRequest.getDeliveryDate());
        renting.setRentingDay(rentingRequest.getRentingDay());
        renting.setRentingAmount(carRepository.findById(rentingRequest.getCarId()).get().getRentPrice()* rentingRequest.getRentingDay());
        renting.setCreatedDate(LocalDateTime.now());

        Renting createdRenting = rentingRepository.save(renting);
        saveUserHistory(createdRenting);

    }

    public void saveUserHistory(Renting renting){
        UserHistory userHistory = new UserHistory();
        userHistory.setUserId(renting.getUserId());
        userHistory.setRentId(renting.getId());
        historyRepository.save(userHistory);
    }



    
}
