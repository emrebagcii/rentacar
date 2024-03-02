package org.bilgeadam.rentacar.controller;

import lombok.RequiredArgsConstructor;
import org.bilgeadam.rentacar.dto.CarListDto;
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

    @PostMapping("/addCar")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Car> addCar(@RequestBody Car car){
        return new ResponseEntity<>(carService.addCar(car),HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<List<Car>> getCarList(){
        return new ResponseEntity<>(carService.getCarList(),HttpStatus.OK);
    }

    @GetMapping("getCarList")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public List<CarListDto> getAllCarListWithCategory(){
        return new ResponseEntity<>(carService.getAllCarListWithCategory(),HttpStatus.OK).getBody();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<Car> getCar(@PathVariable(value = "id") Long id){
        return new ResponseEntity<>(carService.getCar(id),HttpStatus.OK);
    }

    //TODO:baştan yazılacak
    /*
    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CarListDto> updateCar(@RequestBody CarListDto carListDto,@PathVariable(value="id") Long id){
        return new ResponseEntity<>(carService.updateCar(carListDto,id),HttpStatus.OK);
    }*/

    @PutMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void softDeleteCar(@PathVariable(value = "id") Long id){
        carService.softDeleteCar(id);
    }

}
