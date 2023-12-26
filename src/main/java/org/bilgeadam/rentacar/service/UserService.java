package org.bilgeadam.rentacar.service;

import org.bilgeadam.rentacar.model.User;
import org.bilgeadam.rentacar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {



    @Autowired
    private UserRepository userRepository;

    public User addUser(User user){
        if (Objects.isNull(user.getRoles())){
            user.setRoles("ROLE_USER");
        }
        return userRepository.save(user);
    }
}
