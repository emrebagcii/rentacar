package org.bilgeadam.rentacar.controller;

import lombok.RequiredArgsConstructor;
import org.bilgeadam.rentacar.model.Renting;
import org.bilgeadam.rentacar.service.RentingService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rent")
@RequiredArgsConstructor
public class RentingController {

    private final RentingService rentingService;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public void doRent(@RequestBody Renting renting){
        rentingService.doRent(renting);
    }

}
