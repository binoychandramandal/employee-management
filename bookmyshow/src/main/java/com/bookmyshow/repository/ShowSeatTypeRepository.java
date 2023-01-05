package com.bookmyshow.repository;

import com.bookmyshow.entity.Actor;
import com.bookmyshow.entity.ShowSeatType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowSeatTypeRepository extends JpaRepository<ShowSeatType,Long> {

}
