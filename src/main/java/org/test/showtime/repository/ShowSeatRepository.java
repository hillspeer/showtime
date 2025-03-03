package org.test.showtime.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.test.showtime.entities.Location;
import org.test.showtime.entities.Seat;
import org.test.showtime.entities.Show;
import org.test.showtime.entities.ShowSeatAvailability;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ShowSeatRepository extends JpaRepository<ShowSeatAvailability, Long> {

    @Override
    Optional<ShowSeatAvailability> findById(Long aLong);

    List<ShowSeatAvailability> findByShowId(Show showId);

    List<ShowSeatAvailability> findByShowIdAndReservationStatusAndSeatIdIn(Show show,String status, Collection<Seat> seatIds);

    @Override
    <S extends ShowSeatAvailability> S save(S entity);

}