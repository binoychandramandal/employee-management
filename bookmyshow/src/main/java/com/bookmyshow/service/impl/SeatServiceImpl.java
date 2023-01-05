package com.bookmyshow.service.impl;

import com.bookmyshow.converter.EntityVOConverter;
import com.bookmyshow.dto.SeatRequestDto;
import com.bookmyshow.dto.SeatResponseDto;
import com.bookmyshow.entity.Auditorium;
import com.bookmyshow.entity.Seat;
import com.bookmyshow.exception.InvalidAuditoriumException;
import com.bookmyshow.exception.InvalidSeatException;
import com.bookmyshow.repository.AuditoriumRepository;
import com.bookmyshow.repository.SeatRepository;
import com.bookmyshow.service.SeatService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.bookmyshow.converter.EntityVOConverter.convert;

@Service
public class SeatServiceImpl extends BaseServiceImpl<Seat, Long> implements SeatService {
    private SeatRepository seatRepository;
    private AuditoriumRepository auditoriumRepository;

    public SeatServiceImpl(SeatRepository seatRepository,AuditoriumRepository auditoriumRepository) {
        this.seatRepository = seatRepository;
        this.auditoriumRepository=auditoriumRepository;
        setJpaRepository(seatRepository);
    }


    @Override
    public List<SeatResponseDto> findAllByAuditoriumId(Long auditoriumId) {
        return seatRepository.findAllByAuditoriumId(auditoriumId).stream().map(EntityVOConverter::convert).collect(Collectors.toList());
    }

    @Override
    public SeatResponseDto saveSeat(SeatRequestDto seatRequestDto) throws InvalidAuditoriumException {
        Optional<Auditorium> auditoriumOptional = null;
        if (seatRequestDto == null || seatRequestDto.getAuditoriumId() == null || (auditoriumOptional = auditoriumRepository.findById(seatRequestDto.getAuditoriumId())).isEmpty()) {
            throw new InvalidAuditoriumException(seatRequestDto.getAuditoriumId());
        }
        Seat seat = convert(seatRequestDto);
        seat.setAuditorium(auditoriumOptional.get());
        return convert(seatRepository.save(seat));
    }

    @Override
    public SeatResponseDto updateSeat(SeatRequestDto seatRequestDto) throws InvalidSeatException {
        Optional<Seat> seatOptional = null;
        if (seatRequestDto == null || seatRequestDto.getAuditoriumId() == null || (seatOptional = seatRepository.findById(seatRequestDto.getId())).isEmpty()) {
            throw new InvalidSeatException(seatRequestDto.getId());
        }
        Seat seat = convert(seatRequestDto);
        seat.setAuditorium(seatOptional.get().getAuditorium());
        return convert(seatRepository.save(seat));
    }
}
