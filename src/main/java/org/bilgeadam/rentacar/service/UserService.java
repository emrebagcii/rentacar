package org.bilgeadam.rentacar.service;

import lombok.RequiredArgsConstructor;
import org.bilgeadam.rentacar.enums.Role;
import org.bilgeadam.rentacar.model.User;
import org.bilgeadam.rentacar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;


    public User addUser(User user){
        if (Objects.isNull(user.getRoles())){
            user.setRoles(Role.USER);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);

    }
}
