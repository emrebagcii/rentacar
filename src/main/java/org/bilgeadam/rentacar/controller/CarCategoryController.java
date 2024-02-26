package org.bilgeadam.rentacar.controller;

import lombok.RequiredArgsConstructor;
import org.bilgeadam.rentacar.model.CarCategory;
import org.bilgeadam.rentacar.service.CarCategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CarCategoryController {

    private final CarCategoryService categoryService;

    @PostMapping("/addCategory")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CarCategory> addCarCategory(@RequestBody CarCategory carCategory) {
        return new ResponseEntity<>(categoryService.addCarCategory(carCategory), HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<List<CarCategory>> getCarCategoryList() {
        return new ResponseEntity<>(categoryService.getCarCategoryList(), HttpStatus.OK);
    }

    @GetMapping("/active")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<List<CarCategory>> getCarCategoryListActive(){
        return new ResponseEntity<>(categoryService.getCarCategoryListActive(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<CarCategory> getCarCategory(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<>(categoryService.getCarCategory(id), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void updateCarCategory(@RequestBody CarCategory carCategory,@PathVariable(value="id") Long id){
        categoryService.updateCarCategory(carCategory,id);
    }

    @PutMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void softDeleteCarCategory(@PathVariable(value = "id") Long id){
        categoryService.softDeleteCarCategory(id);
    }



}
