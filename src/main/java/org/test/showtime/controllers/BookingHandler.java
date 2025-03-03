package org.test.showtime.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.test.showtime.entities.*;
import org.test.showtime.service.BookingService;
import org.test.showtime.service.ShowService;


import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingHandler {

    @Autowired
    ShowService showService;

    @Autowired
    BookingService bookingService;

//    @Autowired
//    PasswordEncoder passwordEncoder;

//    @PostMapping(value = "/book")
//    void save(@RequestBody List<Seat> show){
//
//        showService.save(show);
//    }

    @GetMapping(value = "/user/{userId}")
    List<Booking> listAll(@PathVariable long userId) {
        return bookingService.listAll(userId);
    }

    @GetMapping(value = "/show/{showId}")
    List<Seat> listShowsByScreen(@PathVariable long showId){
        return bookingService.listAvailableSeats(showId);
    }

    @PostMapping(value = "/save")
    Long save(@RequestBody Booking show){
        return bookingService.save(show);
    }

    @PostMapping(value = "/cancel")
    void save(@RequestBody Show show){
        bookingService.cancel(show);
    }
}
