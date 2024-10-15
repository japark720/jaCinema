package com.jpworld.jacinema.admin.repository;

import com.jpworld.jacinema.admin.domain.TheaterTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TheaterTimeRepository extends JpaRepository<TheaterTime, Long> {

    @Query("SELECT tt FROM TheaterTime tt WHERE tt.theater.id = :theaterId")
    List<TheaterTime> findByTheaterId(@Param("theaterId") Long theaterId);
}
