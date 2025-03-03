package org.test.showtime.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.test.showtime.entities.Location;
import org.test.showtime.entities.Show;
import org.test.showtime.entities.User;

import java.util.List;
import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Long> {

    @Override
    Optional<Location> findById(Long aLong);


    List<Location> findByRegion(String region);


    @Override
    <S extends Location> S save(S entity);
}