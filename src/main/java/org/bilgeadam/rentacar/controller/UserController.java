package org.bilgeadam.rentacar.controller;

import lombok.RequiredArgsConstructor;

import org.bilgeadam.rentacar.dto.LoginDto;
import org.bilgeadam.rentacar.dto.UserDto;
import org.bilgeadam.rentacar.dto.UserListDto;
import org.bilgeadam.rentacar.model.User;
import org.bilgeadam.rentacar.service.JwtService;
import org.bilgeadam.rentacar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private  AuthenticationManager authenticationManager;


    @PostMapping("/addUser")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.addUser(user));
    }

    @PostMapping("/getToken")
    public ResponseEntity<LoginDto> authenticateAndGetToken(@RequestBody UserDto userDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword()));
        if (authentication.isAuthenticated()) {
            return new ResponseEntity<>(jwtService.generateToken(authentication), HttpStatus.OK);
        }

        throw new UsernameNotFoundException("invalid user details.");
    }

    @GetMapping("/active")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public List<UserListDto> getAllByActiveUserList(){
        return new ResponseEntity<>(userService.getAllByActiveUserList(),HttpStatus.OK).getBody();
    }

    @PutMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public void softDeleteUser(@PathVariable(value="id") Long id){
        userService.softDeleteUser(id);
    }

}
