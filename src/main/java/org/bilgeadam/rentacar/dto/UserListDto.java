package org.bilgeadam.rentacar.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bilgeadam.rentacar.enums.Role;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserListDto {

    private Long id;

    @Enumerated(EnumType.STRING)
    private Role roles;

    private String firstName;

    private String lastName;

    private String nationalityNumber;

    private LocalDate birthDate;

    private String email;

    private String city;

    private String district;

    private String addressLine;

    private boolean isActive;
}
