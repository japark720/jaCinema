package com.jpworld.jacinema.admin.repository;

import com.jpworld.jacinema.admin.domain.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Long> {
}
