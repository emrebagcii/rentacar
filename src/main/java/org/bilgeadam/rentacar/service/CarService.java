package org.bilgeadam.rentacar.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.bilgeadam.rentacar.dto.CarListDto;
import org.bilgeadam.rentacar.model.Car;
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
        car.setAvailableCar(0);
        return carRepository.save(car);
    }

    public List<Car> getCarList() {
        return carRepository.findAll();
    }

    public Car getCar(Long id) {
        return carRepository.findById(id).get();
    }

    //TODO:update servisi baştan yazılacak
    /*
    public CarListDto updateCar(CarListDto carListDto, Long id) {

        Car updatedCar = new Car();

        if (carRepository.existsByBrandAndModelAndColorAndTransmissionAndFuelTypeAndYear(carListDto.getBrand(), carListDto.getModel(), carListDto.getColor(), carListDto.getTransmission(), carListDto.getFuelType(),carListDto.getYear())) {
            throw new IllegalArgumentException("Bu araç zaten mevcut");
        }

        updatedCar.setBrand(carListDto.getBrand());
        updatedCar.setModel(carListDto.getModel());
        updatedCar.setCategoryId(carListDto.getCategoryId());
        updatedCar.setColor(carListDto.getColor());
        updatedCar.setTransmission(carListDto.getTransmission());
        updatedCar.setFuelType(carListDto.getFuelType());
        updatedCar.setRentPrice(carListDto.getRentPrice());
        updatedCar.setUpdatedDate(LocalDateTime.now());
        return carRepository.save(updatedCar);
    }*/

    public void softDeleteCar(Long id) {
        carRepository.softDeleteCarById(id);
    }

    //TODO: aracın kiralanıp kiralanmadığı durumun belirlendiği method yazılacak

    public List<CarListDto> getAllCarListWithCategory(){
        return carRepository.getAllCarListWithCategory();
    }
}
