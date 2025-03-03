package org.test.showtime.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.test.showtime.entities.Location;
import org.test.showtime.entities.Seat;
import org.test.showtime.entities.Show;
import org.test.showtime.entities.Theatre;

import java.util.List;
import java.util.Optional;

public interface ShowRepository extends JpaRepository<Show, Long> {

    @Override
    Optional<Show> findById(Long aLong);

    List<Show> findByLocation(Location location);

    @Override
    <S extends Show> S save(S entity);

    List<Show> findByScreenId(Long screenId);

}