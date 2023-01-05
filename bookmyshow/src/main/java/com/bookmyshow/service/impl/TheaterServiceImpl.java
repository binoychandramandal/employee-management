package com.bookmyshow.service.impl;


import com.bookmyshow.dto.TheaterRequestDto;
import com.bookmyshow.dto.TheaterResponseDto;
import com.bookmyshow.entity.City;
import com.bookmyshow.entity.Theater;
import com.bookmyshow.exception.InvalidCityException;
import com.bookmyshow.exception.InvalidTheaterException;
import com.bookmyshow.repository.CityRepository;
import com.bookmyshow.repository.TheaterRepository;
import com.bookmyshow.service.TheaterService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.bookmyshow.converter.EntityVOConverter.convert;

@Service
public class TheaterServiceImpl extends BaseServiceImpl<Theater, Long> implements TheaterService {

    private TheaterRepository theaterRepository;
    private CityRepository cityRepository;

    public TheaterServiceImpl(TheaterRepository theaterRepository, CityRepository cityRepository) {
        this.theaterRepository = theaterRepository;
        this.cityRepository = cityRepository;
        setJpaRepository(this.theaterRepository);
    }

    @Override
    public TheaterResponseDto saveTheater(TheaterRequestDto theaterRequestDto) throws InvalidCityException {
        Optional<City> cityOptional = cityRepository.findById(theaterRequestDto.getCityId());
        if (cityOptional.isEmpty()) {
            throw new InvalidCityException(theaterRequestDto.getCityId());
        }
        Theater theater =convert(theaterRequestDto);
        theater.setCity(cityOptional.get());
        theater = theaterRepository.save(theater);
        return convert(theater);
    }

    @Override
    public TheaterResponseDto updateTheater(TheaterRequestDto theaterRequestDto) throws InvalidTheaterException {
       Optional<Theater> theaterOptional = theaterRepository.findById(theaterRequestDto.getId());
        if (theaterOptional.isEmpty()|| theaterOptional.get().getCity()==null) {
            throw new InvalidTheaterException(theaterRequestDto.getId());
        }
        Theater theater =convert(theaterRequestDto);
        theater.setCity(theaterOptional.get().getCity());
        theater = theaterRepository.save(theater);
        return convert(theater);
    }

    @Override
    public List<TheaterResponseDto> getAllTheaterByCityId(Long cityId) throws InvalidCityException {
        return theaterRepository.findByCityId(cityId).stream().map(x->convert(x)).collect(Collectors.toList());
    }
}
