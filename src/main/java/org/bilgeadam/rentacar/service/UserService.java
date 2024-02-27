package org.bilgeadam.rentacar.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.bilgeadam.rentacar.dto.UserListDto;
import org.bilgeadam.rentacar.enums.Role;
import org.bilgeadam.rentacar.model.User;
import org.bilgeadam.rentacar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
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
        user.setCreatedDate(LocalDateTime.now());
        user.setActive(true);
        return userRepository.save(user);

    }
    public List<UserListDto> getAllByActiveUserList(){
        return userRepository.getAllByActiveUserList();
    }

    @Transactional
    public void softDeleteUser(Long id){
        userRepository.softDeleteUserById(id);
    }
}
