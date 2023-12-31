package org.bilgeadam.rentacar.controller;

import lombok.RequiredArgsConstructor;
import org.bilgeadam.rentacar.model.Car;
import org.bilgeadam.rentacar.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Car> addCar(@RequestBody Car car){
        return new ResponseEntity<>(carService.addCar(car), HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<List<Car>> getCarList(){
        return new ResponseEntity<>(carService.getCarList(),HttpStatus.OK);
    }

    @GetMapping("{/id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<Car> getCar(@PathVariable(value = "id") Long id){
        return new ResponseEntity<>(carService.getCar(id),HttpStatus.OK);
    }

}
