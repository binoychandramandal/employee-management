package com.bookmyshow.repository;

import com.bookmyshow.entity.ShowSeat;
import com.bookmyshow.entity.ShowSeatState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat,Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<ShowSeat> findAllByIdIn(List<Long> showSeatIds);
    List<ShowSeat> findAllByStateEquals(ShowSeatState showSeatState);

}
