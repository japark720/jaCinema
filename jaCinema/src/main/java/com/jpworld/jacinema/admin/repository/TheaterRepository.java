package com.jpworld.jacinema.admin.repository;

import com.jpworld.jacinema.admin.domain.Theater;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheaterRepository extends JpaRepository<Theater, Long> {
}
