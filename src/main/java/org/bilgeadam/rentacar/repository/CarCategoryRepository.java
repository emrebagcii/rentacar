package org.bilgeadam.rentacar.repository;

import org.bilgeadam.rentacar.model.CarCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarCategoryRepository extends JpaRepository<CarCategory,Long> {

    CarCategory findByCategoryName(String categoryName);

    boolean existsByCategoryName(String categoryName);

    @Query("UPDATE CarCategory c set c.categoryName= :categoryName where c.id = :id")
    @Modifying
    void updateCarCategoryById(@Param("categoryName") String categoryName, @Param("id") Long id);

    @Query("UPDATE CarCategory c set c.isActive= false where c.id= :id")
    @Modifying
    void softDeleteCarCategoryById(@Param("id") Long id);

    @Query("SELECT c FROM CarCategory c where c.isActive=true" )
    List<CarCategory> getCarCategoryListActive();

}
