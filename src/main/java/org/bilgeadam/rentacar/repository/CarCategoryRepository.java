package org.bilgeadam.rentacar.repository;

import org.bilgeadam.rentacar.model.CarCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarCategoryRepository extends JpaRepository<CarCategory,Long> {

    CarCategory findByCategoryName(String categoryName);
}
