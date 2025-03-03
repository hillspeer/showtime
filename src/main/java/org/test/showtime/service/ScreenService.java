package org.test.showtime.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.test.showtime.entities.*;
import org.test.showtime.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ScreenService {

    @Autowired
    ScreenRepository screenRepository;

    @Autowired
    ShowRepository showRepository;

    @Autowired
    ShowSeatRepository showSeatRepository;

    @Autowired
    SeatRepository seatRepository;

    public List<Show> listAll(long screenId){
        Screen screen = screenRepository.getById(screenId);
        List<Show> shows = screen.getShows();
        return shows;
    }

    public void save(Screen screen){
//        Location location = locationrepo.getById(show.getLocationId());
//        show.setLocation(location);
        screenRepository.save(screen);
    }

    public void saveShow(List<Show> shows) {

        showRepository.saveAll(shows);


        //Map<Long,List<Show>> showByScreen = shows.stream().collect(Collectors.groupingBy(Show::getScreenId));


        List<Seat> seats = seatRepository.findSeatByScreenId(shows.get(0).getScreenId());

        List<ShowSeatAvailability> ssa= new ArrayList<>();
        for(Show show : shows){

            for(Seat seat : seats) {
                ShowSeatAvailability showSeatAvailability = new ShowSeatAvailability();
                showSeatAvailability.setShowId(show);
                showSeatAvailability.setReservationStatus("AVAILABLE");
                showSeatAvailability.setSeatId(seat);
                ssa.add(showSeatAvailability);
            }

        }

        showSeatRepository.saveAll(ssa);


    }
}
