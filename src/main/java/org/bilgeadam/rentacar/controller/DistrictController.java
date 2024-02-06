package org.bilgeadam.rentacar.controller;

import lombok.RequiredArgsConstructor;
import org.bilgeadam.rentacar.model.City;
import org.bilgeadam.rentacar.model.District;
import org.bilgeadam.rentacar.service.DistrictService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DistrictController {

    private final DistrictService districtService;

    @GetMapping("/city")
    public ResponseEntity<List<City>> getCityList() {
        return new ResponseEntity<>(districtService.getCityList(), HttpStatus.OK);
    }

    @GetMapping("/district/{cityId}")
    public ResponseEntity<List<District>> getDistrictListByCityId(@PathVariable(value = "cityId") Long cityId) {
        return new ResponseEntity<>(districtService.getDistrictListByCityId(cityId), HttpStatus.OK);
    }
}
