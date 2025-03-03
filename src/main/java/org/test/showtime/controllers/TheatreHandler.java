package org.test.showtime.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.test.showtime.entities.Theatre;
import org.test.showtime.service.TheatreService;

import java.util.List;

@RestController
@RequestMapping("/theatre")
public class TheatreHandler {

    @Autowired
    TheatreService theatreService;

//    @Autowired
//    PasswordEncoder passwordEncoder;

    @PostMapping(value = "/save")
    void save(@RequestBody Theatre theatre){

        theatreService.save(theatre);
    }

    @GetMapping(value = "/{locationId}")
    List<Theatre> listAll(@PathVariable long locationId){
        return theatreService.listAll(locationId);
    }
}