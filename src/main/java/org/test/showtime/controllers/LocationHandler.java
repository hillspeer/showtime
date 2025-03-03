package org.test.showtime.controllers;

import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.test.showtime.dto.UserDTO;
import org.test.showtime.entities.Location;
import org.test.showtime.entities.Show;
import org.test.showtime.service.LocationService;
import org.test.showtime.service.ShowService;
import org.test.showtime.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/location")
public class LocationHandler {

    @Autowired
    LocationService locationService;

    @Autowired
    ShowService showService;

//    @Autowired
//    PasswordEncoder passwordEncoder;

    @GetMapping(value = "/fetch/{region}")
    List<Location> fetchByRegion(@PathVariable String region) {
        return locationService.getByRegion(region);
    }

//    @GetMapping(value = "/fetch/{region}")
//    List<Show> fetchById(@PathVariable Long id) {
//        return locationService.getShowsById(id);
//    }

    @PostMapping(value = "/save")
    void save(@RequestBody Location location){
        locationService.saveLocation(location);
    }


}
