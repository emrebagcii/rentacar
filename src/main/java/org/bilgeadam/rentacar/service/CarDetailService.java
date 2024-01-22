package org.bilgeadam.rentacar.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.bilgeadam.rentacar.model.Car;
import org.bilgeadam.rentacar.model.CarDetail;
import org.bilgeadam.rentacar.repository.CarDetailRepository;
import org.bilgeadam.rentacar.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CarDetailService {

    private final CarDetailRepository carDetailRepository;
    private final CarRepository carRepository;

    public void addCarDetail(CarDetail carDetail) {

        if (carDetailRepository.existsByCarPlate(carDetail.getCarPlate())){
            throw new IllegalArgumentException("Bu plakalı araç zaten mevcut");
        }
        carDetail.setRenting(false);
        carDetail.setActive(true);
        carDetail.setCreatedDate(LocalDateTime.now());
        CarDetail createdDetail = carDetailRepository.save(carDetail);
        countCarId(createdDetail.getCarId());

    }

    public void countCarId(Long carId){
        Integer countCar;
        countCar=carDetailRepository.countByCarIdAndIsRentingFalse(carId);
        carRepository.updateAvailableCarById(carId,countCar);
    }

    public List<CarDetail> getCarDetailList(){
        return carDetailRepository.findAll();
    }

    public CarDetail getCarDetail(Long id){
        return carDetailRepository.findById(id).get();
    }

    public CarDetail updateCarDetail(CarDetail carDetail) {

        if (carDetailRepository.existsByCarPlate(carDetail.getCarPlate())) {
            throw new IllegalArgumentException("Bu plakalı araç zaten mevcut");
        }
        carDetail.setUpdatedDate(LocalDateTime.now());
        return carDetailRepository.save(carDetail);
    }

}
