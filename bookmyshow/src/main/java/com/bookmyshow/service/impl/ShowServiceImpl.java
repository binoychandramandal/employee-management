package com.bookmyshow.service.impl;

import com.bookmyshow.converter.EntityVOConverter;
import com.bookmyshow.dto.ShowRequestDto;
import com.bookmyshow.dto.ShowResponseDto;
import com.bookmyshow.entity.*;
import com.bookmyshow.exception.InvalidAuditoriumException;
import com.bookmyshow.exception.InvalidMovieException;
import com.bookmyshow.repository.*;
import com.bookmyshow.service.ShowService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.bookmyshow.converter.EntityVOConverter.convert;

@Service
public class ShowServiceImpl extends BaseServiceImpl<Show, Long> implements ShowService {
    private final ShowRepository showRepository;
    private final AuditoriumRepository auditoriumRepository;
    private final MovieRepository movieRepository;
    private final ShowSeatRepository showSeatRepository;
    private final ShowSeatTypeRepository showSeatTypeRepository;

    public ShowServiceImpl(ShowSeatTypeRepository showSeatTypeRepository,ShowRepository showRepository, AuditoriumRepository auditoriumRepository, MovieRepository movieRepository, ShowSeatRepository showSeatRepository) {
        this.showRepository = showRepository;
        this.auditoriumRepository = auditoriumRepository;
        this.movieRepository = movieRepository;
        this.showSeatRepository = showSeatRepository;
        this.showSeatTypeRepository = showSeatTypeRepository;
        setJpaRepository(showRepository);
    }

    @Override
    public ShowResponseDto createShow(ShowRequestDto showRequestDto) throws InvalidMovieException, InvalidAuditoriumException {
        if (showRequestDto == null || showRequestDto.getAuditoriumId() == null || showRequestDto.getSeatPricing()==null) {
            return null;
        }
        Optional<Auditorium> auditoriumOptional = Optional.empty();
        Optional<Movie> movieOptional = Optional.empty();
        if (showRequestDto.getMovieId() == null || (movieOptional = movieRepository.findById(showRequestDto.getMovieId())).isEmpty()) {
            throw new InvalidMovieException(showRequestDto.getMovieId());
        }
        if (showRequestDto.getAuditoriumId() == null || (auditoriumOptional = auditoriumRepository.findById(showRequestDto.getAuditoriumId())).isEmpty()) {
            throw new InvalidAuditoriumException(showRequestDto.getMovieId());
        }
        Show show = convert(showRequestDto);
        show.setMovie(movieOptional.get());
        show.setAuditorium(auditoriumOptional.get());
        Show savedShow = showRepository.save(show);
        List<ShowSeat> savedShowSeats = new ArrayList<>();
        for (Seat seat : auditoriumOptional.get().getSeats()) {
            ShowSeat showSeat = new ShowSeat();
            showSeat.setShow(savedShow);
            showSeat.setSeat(seat);
            showSeat.setState(ShowSeatState.AVAILABLE);
            savedShowSeats.add(showSeatRepository.save(showSeat));
        }

        List<ShowSeatType> savedShowSeatType = new ArrayList<>();
        for (Map.Entry<SeatType,Integer>  entry: showRequestDto.getSeatPricing().entrySet()) {
            ShowSeatType showSeatType = new ShowSeatType();
            showSeatType.setShow(savedShow);
            showSeatType.setSeatType(entry.getKey());
            showSeatType.setPrice(entry.getValue());
            savedShowSeatType.add(showSeatTypeRepository.save(showSeatType));
        }
        savedShow.setShowSeats(savedShowSeats);
        savedShow.setShowSeatTypes(savedShowSeatType);
        return convert(showRepository.save(savedShow));
    }

    @Override
    public ShowResponseDto updateShow(ShowRequestDto showRequestDto) {
        return null;
    }

    @Override
    public List<ShowResponseDto> findAllByAuditoriumId(Long auditoriumId) {
        return showRepository.findAllByAuditoriumId(auditoriumId).stream().map(EntityVOConverter::convert).collect(Collectors.toList());
    }

    @Override
    public List<ShowResponseDto> findAllByMovieId(Long movieId) {
        return showRepository.findAllByMovieId(movieId).stream().map(EntityVOConverter::convert).collect(Collectors.toList());
    }

    @Override
    public List<ShowResponseDto> findAllByMovieId_CityId_(Long movieId, Long cityId) {
       return  showRepository.findAllByAuditoriumTheaterCityIdAndMovieId(cityId,movieId).stream().map(EntityVOConverter::convert).collect(Collectors.toList());
    }

    @Override
    public List<ShowResponseDto> findAllByMovieId_LanguageIn_CityId_(Long movieId, List<Language> languages, Long cityId) {
        return  showRepository.findAllByMovieIdAndMovieLanguagesInAndAuditoriumTheaterCityId(movieId,languages,cityId).stream().map(EntityVOConverter::convert).collect(Collectors.toList());
    }
}
