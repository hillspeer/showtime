package org.test.showtime.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.test.showtime.entities.Booking;
import org.test.showtime.entities.Location;
import org.test.showtime.entities.Show;
import org.test.showtime.entities.User;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Override
    Optional<Booking> findById(Long aLong);

    Optional<List<Booking>> findAllByUser(User user);

    @Override
    <S extends Booking> S save(S entity);

    List<Booking> getBookingByShowId(Long showId);

    List<Booking> getBookingByShowIdAndUser(Long showId, User user);

    Booking getBookingById(Long id);
}