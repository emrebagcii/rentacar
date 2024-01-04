package org.bilgeadam.rentacar.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.bilgeadam.rentacar.model.Car;
import org.bilgeadam.rentacar.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CarService {

    private final CarRepository carRepository;

    public Car addCar(Car car) {

        if (carRepository.existsByBrandAndModelAndColorAndTransmissionAndFuelType(car.getBrand(), car.getModel(), car.getColor(), car.getTransmission(), car.getFuelType())) {
            throw new IllegalArgumentException("Bu araç zaten mevcut");
        }
        car.setActive(setStatus(car));
        return carRepository.save(car);
    }

    public boolean carExist(Car car) {

        boolean result;
        Car existingCar = carRepository.findByBrandAndModelAndColorAndTransmissionAndFuelType(car.getBrand(), car.getModel(), car.getColor(), car.getTransmission(), car.getFuelType());

        if (existingCar != null
                && existingCar.getBrand().equals(car.getBrand())
                && existingCar.getModel().equals(car.getModel())
                && existingCar.getColor().equals(car.getColor())
                && existingCar.getTransmission() == car.getTransmission()
                && existingCar.getFuelType() == car.getFuelType()) {
            result = false;
        } else {
            result = true;
        }
        return result;
    }

    public boolean setStatus(Car car) {
        boolean isActive;

        if (carExist(car) == true) {
            isActive = true;
        } else {
            isActive = false;
        }

        return isActive;
    }

    public List<Car> getCarList() {
        return carRepository.findAll();
    }

    public Car getCar(Long id) {
        return carRepository.findById(id).get();
    }

    public Car updateCar(Car car) {

        if (carRepository.existsByBrandAndModelAndColorAndTransmissionAndFuelType(car.getBrand(), car.getModel(), car.getColor(), car.getTransmission(), car.getFuelType())) {
            throw new IllegalArgumentException("Bu araç zaten mevcut");
        }

        return carRepository.save(car);
    }

    public void softDeleteCar(Long id) {
        carRepository.softDeleteCarById(id);
    }

    //TODO: aracın kiralanıp kiralanmadığı durumun belirlendiği method yazılacak
}
