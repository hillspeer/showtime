package org.test.showtime.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.test.showtime.entities.Location;
import org.test.showtime.entities.Theatre;

import java.util.List;
import java.util.Optional;

public interface TheatreRepository extends JpaRepository<Theatre, Long> {

    @Override
    Optional<Theatre> findById(Long aLong);

    List<Theatre> findByLocation(Location location);

    @Override
    <S extends Theatre> S save(S entity);
}