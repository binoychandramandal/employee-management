package com.bookmyshow.repository;

import com.bookmyshow.entity.Language;
import com.bookmyshow.entity.Show;
import com.bookmyshow.entity.ShowSeat;
import com.bookmyshow.entity.ShowSeatState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.List;

@Repository
public interface ShowRepository extends JpaRepository<Show,Long> {
    List<Show> findAllByMovieId(Long movieId);
    List<Show> findAllByAuditoriumId(Long movieId);
    List<Show> findAllByAuditoriumTheaterCityIdAndMovieId(Long movieId,Long cityId);
    List<Show> findAllByMovieIdAndMovieLanguagesInAndAuditoriumTheaterCityId(Long movieId, List<Language> languages,Long cityId);

}
