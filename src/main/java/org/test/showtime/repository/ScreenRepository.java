package org.test.showtime.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.test.showtime.entities.Location;
import org.test.showtime.entities.Screen;
import org.test.showtime.entities.Show;

import java.util.List;
import java.util.Optional;

public interface ScreenRepository extends JpaRepository<Screen, Long> {

    @Override
    Optional<Screen> findById(Long aLong);

    @Override
    <S extends Screen> S save(S entity);

}