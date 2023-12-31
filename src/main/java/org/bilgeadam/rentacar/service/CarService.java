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

    public Car addCar(Car car){
        return carRepository.save(car);

        //TODO: aynı marka-model-vites-renk araç eklenemesin
    }

    public List<Car> getCarList(){
        return carRepository.findAll();
    }

    public Car getCar(Long id){
        return carRepository.findById(id).get();
    }

    //TODO: id le update methodu eklenecek
    //TODO: soft delete eklenecek
    //TODO: Car modelde bulunan bazı attributeler ENUM yapılabilir. Örneğin vites tipi
    //TODO: aracın kiralanıp kiralanmadığı durumun belirlendiği method yazılacak
}
