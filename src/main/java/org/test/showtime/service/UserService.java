package org.test.showtime.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.test.showtime.dto.UserDTO;
import org.test.showtime.entities.Location;
import org.test.showtime.entities.User;
import org.test.showtime.mapper.UserMapper;
import org.test.showtime.repository.LocationRepository;
import org.test.showtime.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    private UserMapper userMapper;

    //@Autowired
    //PasswordEncoder passwordEncoder;

    public void saveUser(UserDTO userDTO) {

        User user = userMapper.getUserEntity(userDTO);
        Location l = locationRepository.findById(userDTO.getLocation()).get();
        //user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setUserLocation(l);
        userRepository.save(user);
    }

    public UserDetails loadUserByUsername(String email){
        User user = userRepository.findByEmail(email).getFirst();
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .build();
    }
}
