package org.test.showtime.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.test.showtime.entities.Location;
import org.test.showtime.entities.Show;
import org.test.showtime.mapper.UserMapper;
import org.test.showtime.repository.LocationRepository;

import java.util.List;

@Service
public class LocationService {

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    private UserMapper userMapper;

    //@Autowired
    //PasswordEncoder passwordEncoder;

    public Location getById(Long aLong) {
        return locationRepository.getById(aLong);
    }

    public List<Location> getByRegion(String region) {
        return locationRepository.findByRegion(region);
    }

//    public List<Show> getShowsById(Long id) {
//        return locationRepository.findById(id).get().getShows();
//    }

    public void saveLocation(Location location) {
        locationRepository.save(location);
    }

}
