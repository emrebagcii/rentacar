package org.bilgeadam.rentacar.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.bilgeadam.rentacar.model.CarCategory;
import org.bilgeadam.rentacar.repository.CarCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CarCategoryService {

    private final CarCategoryRepository categoryRepository;

    public CarCategory addCarCategory(CarCategory carCategory) {
        carCategory.setActive(setStatus(carCategory));
        return categoryRepository.save(carCategory);

        //TODO:aynı isimde kategori bir daha eklenmesin
    }

    public boolean categoryExist(CarCategory carCategory) {
        boolean result;
        CarCategory existCategory = categoryRepository.findByCategoryName(carCategory.getCategoryName());

        if (existCategory == null) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    public boolean setStatus(CarCategory carCategory) {

        boolean isActive;

        if (categoryExist(carCategory) == true) {
            isActive=true;
        }else{
            isActive=false;
        }
        return isActive;
    }

    public List<CarCategory> getCarCategoryList() {
        return categoryRepository.findAll();
    }

    public CarCategory getCarCategory(Long id) {
        return categoryRepository.findById(id).get();
    }

        //TODO: update methodu id alınarak yapılacak
        //TODO:soft-delete

}
