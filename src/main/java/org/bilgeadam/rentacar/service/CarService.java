package org.bilgeadam.rentacar.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.bilgeadam.rentacar.model.Car;
import org.bilgeadam.rentacar.repository.CarDetailRepository;
import org.bilgeadam.rentacar.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CarService {

    private final CarRepository carRepository;


    public Car addCar(Car car){

        if (carRepository.existsByBrandAndModelAndColorAndTransmissionAndFuelTypeAndYear(car.getBrand(), car.getModel(), car.getColor(), car.getTransmission(), car.getFuelType(),car.getYear())) {
            throw new IllegalArgumentException("Bu araç zaten mevcut");
        }
        car.setActive(true);
        car.setCreatedDate(LocalDateTime.now());
        return  carRepository.save(car);
    }

    public List<Car> getCarList() {
        return carRepository.findAll();
    }

    public Car getCar(Long id) {
        return carRepository.findById(id).get();
    }

    public Car updateCar(Car car) {

        if (carRepository.existsByBrandAndModelAndColorAndTransmissionAndFuelTypeAndYear(car.getBrand(), car.getModel(), car.getColor(), car.getTransmission(), car.getFuelType(),car.getYear())) {
            throw new IllegalArgumentException("Bu araç zaten mevcut");
        }
        car.setUpdatedDate(LocalDateTime.now());
        return carRepository.save(car);
    }

    public void softDeleteCar(Long id) {
        carRepository.softDeleteCarById(id);
    }

    //TODO: aracın kiralanıp kiralanmadığı durumun belirlendiği method yazılacak
}
