package org.test.showtime.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.test.showtime.dto.UserDTO;
import org.test.showtime.entities.User;
import org.test.showtime.service.UserService;

import java.util.Date;

@RestController
@RequestMapping("/user")
public class ProfileHandler {

    @Autowired
    UserService userService;

//    @Autowired
//    PasswordEncoder passwordEncoder;

    @PostMapping(value = "/save")
    void save(@RequestBody UserDTO user){
        userService.saveUser(user);
    }
}
