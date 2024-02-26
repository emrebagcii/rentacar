package org.bilgeadam.rentacar.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.bilgeadam.rentacar.model.CarCategory;
import org.bilgeadam.rentacar.repository.CarCategoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CarCategoryService {

    private final CarCategoryRepository categoryRepository;

    public CarCategory addCarCategory(CarCategory carCategory) {

        if (categoryRepository.existsByCategoryName(carCategory.getCategoryName())){
            throw new IllegalArgumentException("Bu kategori zaten mevcut");
        }
        carCategory.setActive(true);
        carCategory.setCreatedDate(LocalDateTime.now());
        return categoryRepository.save(carCategory);
    }

    public List<CarCategory> getCarCategoryList() {
        return categoryRepository.findAll();
    }

    public List<CarCategory> getCarCategoryListActive() {
        return categoryRepository.getCarCategoryListActive();
    }

    public CarCategory getCarCategory(Long id) {
        return categoryRepository.findById(id).get();
    }

    public void updateCarCategory(CarCategory carCategory,Long id){
         categoryRepository.updateCarCategoryById(carCategory.getCategoryName(),id);
    }

    public void softDeleteCarCategory(Long id){
        categoryRepository.softDeleteCarCategoryById(id);
    }

}
