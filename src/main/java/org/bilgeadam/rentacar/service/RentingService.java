package org.bilgeadam.rentacar.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.bilgeadam.rentacar.dto.RentDto;
import org.bilgeadam.rentacar.dto.RentingRequest;
import org.bilgeadam.rentacar.model.Car;
import org.bilgeadam.rentacar.model.CarDetail;
import org.bilgeadam.rentacar.model.Renting;
import org.bilgeadam.rentacar.model.UserHistory;
import org.bilgeadam.rentacar.repository.CarDetailRepository;
import org.bilgeadam.rentacar.repository.CarRepository;
import org.bilgeadam.rentacar.repository.RentingRepository;
import org.bilgeadam.rentacar.repository.UserHistoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Transactional
public class RentingService {

    private final RentingRepository rentingRepository;
    private final CarRepository carRepository;
    private final CarDetailRepository carDetailRepository;
    private final UserHistoryRepository historyRepository;

    //TODO: mapStruct kullan
    public void doRent(RentingRequest rentingRequest){
        Renting renting = new Renting();
        Integer rentingDay;

        renting.setCarId(rentingRequest.getCarId());
        renting.setUserId(rentingRequest.getUserId());
        renting.setDriverLicenceType(rentingRequest.getDriverLicenceType());
        renting.setDriverLicenceNo(rentingRequest.getDriverLicenceNo());
        renting.setRentCityId(rentingRequest.getRentCityId());
        renting.setDeliveryCityId(rentingRequest.getDeliveryCityId());
        renting.setRentingDate(rentingRequest.getRentingDate());
        renting.setDeliveryDate(rentingRequest.getDeliveryDate());
        rentingDay= Math.toIntExact(ChronoUnit.DAYS.between(rentingRequest.getRentingDate(), rentingRequest.getDeliveryDate()));
        renting.setRentingDay(rentingDay);
        renting.setRentingAmount(carRepository.findById(rentingRequest.getCarId()).get().getRentPrice()* rentingDay);
        renting.setCreatedDate(LocalDateTime.now());
        renting.setRentingState(true);
        Renting createdRenting = rentingRepository.save(renting);

        carDetailRepository.updateIsRentingById(selectCarDetail(createdRenting.getCarId()));
        carRepository.updateRentingAvailableCarById(createdRenting.getCarId());
        saveUserHistory(createdRenting);
    }

    public void saveUserHistory(Renting renting){
        UserHistory userHistory = new UserHistory();
        userHistory.setUserId(renting.getUserId());
        userHistory.setRentId(renting.getId());
        historyRepository.save(userHistory);
    }

    public Long selectCarDetail(Long carId){
        Long maxKilometerCarId;
        maxKilometerCarId = carDetailRepository.findRentingByCarIdAndIsActiveTrueAndIsRentingFalse(carId);
        return maxKilometerCarId;
    }

    public List<Renting> getRentingList(){
        return rentingRepository.findAll();
    }

    public Renting getRenting(Long id){
        return rentingRepository.findById(id).get();
    }

    public void endRenting(Long id){

        Renting renting = new Renting();
        renting=getRenting(id);

        rentingRepository.endRenting(id);
        endRentingCar(renting.getCarId());

    }

    public RentDto getRentingByCarIdAndRentingState(Long carId){
        return rentingRepository.getRentingByCarIdAndRentingState(carId);
    }

    public void endRentingCar(Long carId){
        carDetailRepository.endRentingCar(carId);
    }

}
