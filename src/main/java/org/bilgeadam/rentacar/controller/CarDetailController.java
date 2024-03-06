package org.bilgeadam.rentacar.controller;

import lombok.RequiredArgsConstructor;
import org.bilgeadam.rentacar.dto.CarDetailDto;
import org.bilgeadam.rentacar.model.Car;
import org.bilgeadam.rentacar.model.CarDetail;
import org.bilgeadam.rentacar.service.CarDetailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cardetail")
@RequiredArgsConstructor
public class CarDetailController {

    private final CarDetailService carDetailService;

    @PostMapping("/addcardetail")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void addCarDetail(@RequestBody CarDetail carDetail) {
        carDetailService.addCarDetail(carDetail);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<List<CarDetail>> getCarDetailList() {
        return new ResponseEntity<>(carDetailService.getCarDetailList(), HttpStatus.OK);
    }

    @GetMapping("/inventory")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public List<CarDetailDto> getInventoryList(){
        return new ResponseEntity<>(carDetailService.getInventoryList(),HttpStatus.OK).getBody();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<CarDetail> getCarDetail(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<>(carDetailService.getCarDetail(id), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CarDetail> updateCar(@RequestBody CarDetail carDetail){
        CarDetail updatedCarDetail = carDetailService.updateCarDetail(carDetail);
        return new ResponseEntity<>(updatedCarDetail,HttpStatus.OK);
    }

    @PutMapping("/endrent/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public void endRentingCar(@PathVariable(value = "id") Long id){
        carDetailService.endRentingCar(id);
    }

}
