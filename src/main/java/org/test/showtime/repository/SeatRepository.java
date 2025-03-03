package org.test.showtime.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.test.showtime.entities.Location;
import org.test.showtime.entities.Seat;
import org.test.showtime.entities.Show;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface SeatRepository extends JpaRepository<Seat, Long> {

    @Override
    Optional<Seat> findById(Long aLong);

    @Override
    <S extends Seat> S save(S entity);

    List<Seat> getSeatsByIdIsIn(Collection<Long> ids);

    List<Seat> findSeatByScreenId(Long screenId);
}