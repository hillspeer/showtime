package org.test.showtime.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.test.showtime.entities.Screen;
import org.test.showtime.entities.Show;
import org.test.showtime.entities.Theatre;
import org.test.showtime.repository.ShowRepository;
import org.test.showtime.service.ScreenService;
import org.test.showtime.service.TheatreService;

import java.util.List;

@RestController
@RequestMapping("/screen")
public class ScreenHandler {

    @Autowired
    ScreenService screenService;


//    @Autowired
//    PasswordEncoder passwordEncoder;

    @PostMapping(value = "/save")
    void save(@RequestBody Screen screen){

        screenService.save(screen);
    }

    @PostMapping(value = "/saveShow")
    void saveShow(@RequestBody List<
            Show> screen){
        screenService.saveShow(screen);
    }

    @GetMapping(value = "/{screenId}")
    List<Show> listAll(@PathVariable long screenId){
        return screenService.listAll(screenId);
    }
}