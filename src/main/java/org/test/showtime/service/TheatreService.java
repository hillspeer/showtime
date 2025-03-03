package org.test.showtime.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.test.showtime.entities.Location;
import org.test.showtime.entities.Theatre;
import org.test.showtime.repository.LocationRepository;
import org.test.showtime.repository.TheatreRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheatreService {

    @Autowired
    LocationRepository locationrepo;

    @Autowired
    TheatreRepository theatreRepository;

    public List<Theatre> listAll(long locationId){
        Location location = locationrepo.getById(locationId);
        List<Theatre> theatres = theatreRepository.findByLocation(location);
        return theatres;
    }

    public void save(Theatre theatre){
        Location location = locationrepo.getById(theatre.getLocationId());
        //theatre.setLocation(location);
        theatreRepository.save(theatre);
    }
}
