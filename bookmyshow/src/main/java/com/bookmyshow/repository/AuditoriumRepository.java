package com.bookmyshow.repository;

import com.bookmyshow.entity.Auditorium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuditoriumRepository extends JpaRepository<Auditorium,Long> {
    List<Auditorium> findAllByTheaterId(Long theaterId);
}
