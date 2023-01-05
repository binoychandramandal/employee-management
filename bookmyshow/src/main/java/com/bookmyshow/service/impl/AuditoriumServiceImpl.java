package com.bookmyshow.service.impl;

import com.bookmyshow.converter.EntityVOConverter;
import com.bookmyshow.dto.AuditoriumRequestDto;
import com.bookmyshow.dto.AuditoriumResponseDto;
import com.bookmyshow.entity.Auditorium;
import com.bookmyshow.entity.Theater;
import com.bookmyshow.exception.InvalidAuditoriumException;
import com.bookmyshow.exception.InvalidTheaterException;
import com.bookmyshow.repository.AuditoriumRepository;
import com.bookmyshow.repository.CityRepository;
import com.bookmyshow.repository.TheaterRepository;
import com.bookmyshow.service.AuditoriumService;
import org.springframework.stereotype.Service;

import static com.bookmyshow.converter.EntityVOConverter.convert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuditoriumServiceImpl extends BaseServiceImpl<Auditorium, Long> implements AuditoriumService {
    private AuditoriumRepository auditoriumRepository;
    private CityRepository cityRepository;

    private TheaterRepository theaterRepository;

    public AuditoriumServiceImpl(AuditoriumRepository auditoriumRepository, CityRepository cityRepository,TheaterRepository theaterRepository) {
        this.auditoriumRepository = auditoriumRepository;
        this.cityRepository = cityRepository;
        this.theaterRepository =theaterRepository;
        setJpaRepository(auditoriumRepository);
    }


    @Override
    public AuditoriumResponseDto saveAuditorium(AuditoriumRequestDto auditoriumRequestDto) throws InvalidTheaterException {
        Optional<Theater> theaterOptional = null;
        if (auditoriumRequestDto == null || auditoriumRequestDto.getTheaterId() == null || (theaterOptional = theaterRepository.findById(auditoriumRequestDto.getTheaterId())).isEmpty()) {
            throw new InvalidTheaterException(null);
        }
        Auditorium auditorium = convert(auditoriumRequestDto);
        auditorium.setTheater(theaterOptional.get());
        return convert(auditoriumRepository.save(auditorium));
    }

    @Override
    public List<AuditoriumResponseDto> getAllAuditoriumByTheaterId(Long theaterId) {
        return auditoriumRepository.findAllByTheaterId(theaterId).stream().map(EntityVOConverter::convert).collect(Collectors.toList());
    }

    @Override
    public AuditoriumResponseDto updateAuditorium(AuditoriumRequestDto auditoriumRequestDto) throws InvalidAuditoriumException {
        Optional<Auditorium> auditoriumOptional = null;
        if (auditoriumRequestDto == null || auditoriumRequestDto.getId() == null || (auditoriumOptional = auditoriumRepository.findById(auditoriumRequestDto.getId())).isEmpty()) {
            throw new InvalidAuditoriumException(auditoriumRequestDto.getId());
        }
        Auditorium auditorium = convert(auditoriumRequestDto);
        auditorium.setTheater(auditoriumOptional.get().getTheater());
        return convert(auditoriumRepository.save(auditorium));
    }
}
