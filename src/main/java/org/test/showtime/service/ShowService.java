package org.test.showtime.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.test.showtime.entities.Location;
import org.test.showtime.entities.Show;
import org.test.showtime.entities.Theatre;
import org.test.showtime.repository.LocationRepository;
import org.test.showtime.repository.ShowRepository;
import org.test.showtime.repository.TheatreRepository;

import java.util.List;

@Service
public class ShowService {

    @Autowired
    LocationRepository locationrepo;

    @Autowired
    ShowRepository showRepository;

    public List<Show> listAll(long locationId){
        Location location = locationrepo.getById(locationId);
        List<Show> shows = showRepository.findByLocation(location);
        return shows;
    }

    public void save(Show show){
//        Location location = locationrepo.getById(show.getLocationId());
//        show.setLocation(location);
        showRepository.save(show);
    }
}
