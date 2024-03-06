package org.bilgeadam.rentacar.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.bilgeadam.rentacar.dto.RentDto;
import org.bilgeadam.rentacar.dto.RentingRequest;
import org.bilgeadam.rentacar.model.Renting;
import org.bilgeadam.rentacar.service.RentingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rent")
@RequiredArgsConstructor
public class RentingController {

    private final RentingService rentingService;


    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public void doRent(@RequestBody RentingRequest rentingRequest){
        rentingService.doRent(rentingRequest);
    }

    @GetMapping("/allrent")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<List<Renting>> getRentingList(){
        return new ResponseEntity<>(rentingService.getRentingList(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Renting> getRenting(@PathVariable(value = "id") Long id){
        return new ResponseEntity<>(rentingService.getRenting(id), HttpStatus.OK);
    }

    @PutMapping("/endrent/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public void endRenting(@PathVariable(value = "id") Long id){
        rentingService.endRenting(id);
    }

    @GetMapping("/getrentbycar/{carId}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<RentDto> getRentingByCarIdAndRentingState(@PathVariable(value = "carId") Long carId){
        return new ResponseEntity<>(rentingService.getRentingByCarIdAndRentingState(carId),HttpStatus.OK);
    }

}
