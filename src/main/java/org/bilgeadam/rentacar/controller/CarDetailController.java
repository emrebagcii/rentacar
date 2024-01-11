package org.bilgeadam.rentacar.controller;

import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<CarDetail> addCarDetail(@RequestBody CarDetail carDetail) {
        return new ResponseEntity<>(carDetailService.addCarDetail(carDetail), HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<List<CarDetail>> getCarDetailList() {
        return new ResponseEntity<>(carDetailService.getCarDetailList(), HttpStatus.OK);
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

}
