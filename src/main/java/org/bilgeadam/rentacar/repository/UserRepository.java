package org.bilgeadam.rentacar.repository;


import org.bilgeadam.rentacar.dto.UserListDto;
import org.bilgeadam.rentacar.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @Query("select new org.bilgeadam.rentacar.dto.UserListDto(u.id, u.roles, u.firstName, u.lastName, u.nationalityNumber, u.birthDate, u.email, c.cityName, d.districtName, u.addressLine,u.isActive) from User u inner join City c on c.id=u.cityId inner join District d on d.id=u.districtId")
    List<UserListDto> getAllByActiveUserList();

    @Query("UPDATE User u set u.isActive=false WHERE u.id=:id")
    @Modifying
    void softDeleteUserById(@Param("id") Long id);
}
