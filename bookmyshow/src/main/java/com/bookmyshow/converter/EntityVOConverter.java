package com.bookmyshow.converter;

import com.bookmyshow.dto.*;
import com.bookmyshow.entity.*;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.stream.Collectors;

public class EntityVOConverter {

    public static Actor convert(ActorRequestDto requestDto) {
        if (requestDto == null)
            return null;
        Actor actor = new Actor();
        actor.setName(requestDto.getName());
        actor.setId(requestDto.getId());
        return actor;
    }

    public static ActorResponseDto convert(Actor actor) {
        if (actor == null)
            return null;
        ActorResponseDto actorResponseDto = new ActorResponseDto();
        actorResponseDto.setName(actor.getName());
        actorResponseDto.setId(actor.getId());
        return actorResponseDto;
    }

    public static CityResponseDto convert(City city) {
        if (city == null)
            return null;
        CityResponseDto cityResponseDto = new CityResponseDto();
        cityResponseDto.setName(city.getName());
        cityResponseDto.setId(city.getId());
        return cityResponseDto;
    }


    public static City convert(CityRequestDto cityRequestDto) {
        if (cityRequestDto == null)
            return null;
        City city = new City();
        city.setName(cityRequestDto.getName());
        city.setId(cityRequestDto.getId());
        return city;
    }

    public static Theater convert(TheaterRequestDto theaterRequestDto) {
        if (theaterRequestDto == null)
            return null;
        Theater theater = new Theater();
        theater.setName(theaterRequestDto.getName());
        theater.setId(theaterRequestDto.getId());
        theater.setAddress(theaterRequestDto.getAddress());
        return theater;
    }

    public static TheaterResponseDto convert(Theater theater) {
        if (theater == null)
            return null;
        TheaterResponseDto theaterResponseDto = new TheaterResponseDto();
        theaterResponseDto.setName(theater.getName());
        theaterResponseDto.setId(theater.getId());
        theaterResponseDto.setAddress(theater.getAddress());
        theaterResponseDto.setCity(convert(theater.getCity()));
        return theaterResponseDto;
    }


    public static AuditoriumResponseDto convert(Auditorium auditorium) {
        if (auditorium == null)
            return null;
        AuditoriumResponseDto auditoriumResponseDto = new AuditoriumResponseDto();
        auditoriumResponseDto.setName(auditorium.getName());
        auditoriumResponseDto.setId(auditorium.getId());
        auditoriumResponseDto.setFeatures(auditorium.getFeatures());
        auditoriumResponseDto.setTheater(convert(auditorium.getTheater()));
        return auditoriumResponseDto;
    }

    public static Auditorium convert(AuditoriumRequestDto auditoriumRequestDto) {
        if (auditoriumRequestDto == null)
            return null;
        Auditorium auditorium = new Auditorium();
        auditorium.setName(auditoriumRequestDto.getName());
        auditorium.setId(auditoriumRequestDto.getId());
        auditorium.setFeatures(auditoriumRequestDto.getFeatures());
        return auditorium;
    }

    public static Seat convert(SeatRequestDto seatRequestDto) {

        if (seatRequestDto == null)
            return null;
        Seat seat = new Seat();
        seat.setName(seatRequestDto.getName());
        seat.setId(seatRequestDto.getId());
        seat.setRow(seatRequestDto.getRow());
        seat.setColumn(seatRequestDto.getColumn());
        seat.setSeatType(seatRequestDto.getSeatType());
        seat.setStatus(seatRequestDto.getStatus());
        return seat;
    }

    public static SeatResponseDto convert(Seat seat) {
        if (seat == null)
            return null;
        SeatResponseDto seatResponseDto = new SeatResponseDto();
        seatResponseDto.setId(seat.getId());
        seatResponseDto.setRow(seat.getRow());
        seatResponseDto.setColumn(seat.getColumn());
        seatResponseDto.setSeatType(seat.getSeatType());
        seatResponseDto.setStatus(seat.getStatus());
        seatResponseDto.setName(seat.getName());
        return seatResponseDto;
    }

    public static UserResponseDto convert(User user) {
        if (user == null)
            return null;
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setName(user.getName());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setPhone(user.getPhone());
        userResponseDto.setAddress(user.getAddress());
        return userResponseDto;
    }

    public static User convert(UserRequestDto userRequestDto) {
        if (userRequestDto == null)
            return null;
        User user = new User();
        user.setId(userRequestDto.getId());
        user.setName(userRequestDto.getName());
        user.setEmail(userRequestDto.getEmail());
        user.setPhone(userRequestDto.getPhone());
        user.setAddress(userRequestDto.getAddress());
        return user;
    }

    public static MovieResponseDto convert(Movie movie) {
        if (movie == null) {
            return null;
        }
        MovieResponseDto movieResponseDto = new MovieResponseDto();
        movieResponseDto.setId(movie.getId());
        movieResponseDto.setName(movie.getName());
        movieResponseDto.setGenres(movie.getGenres());
        movieResponseDto.setLanguages(movie.getLanguages());
        movieResponseDto.setFeatures(movie.getFeatures());
        if (!CollectionUtils.isEmpty(movie.getActors())) {
            movieResponseDto.setActors(movie.getActors().stream().map(EntityVOConverter::convert).collect(Collectors.toList()));
        }
        return movieResponseDto;
    }

    public static Movie convert(MovieRequestDto movieRequestDto) {
        if (movieRequestDto == null)
            return null;
        Movie movie = new Movie();
        movie.setName(movieRequestDto.getName());
        movie.setId(movieRequestDto.getId());
        movie.setFeatures(movieRequestDto.getFeatures());
        movie.setGenres(movieRequestDto.getGenres());
        movie.setLanguages(movieRequestDto.getLanguages());
        if (!CollectionUtils.isEmpty(movieRequestDto.getActors()))
            movie.setActors(movieRequestDto.getActors().stream().map(EntityVOConverter::convert).collect(Collectors.toList()));

        return movie;
    }

    public static Show convert(ShowRequestDto showRequestDto) {
        if (showRequestDto == null)
            return null;
        Show show = new Show();
        show.setId(showRequestDto.getId());
        show.setStartTime(showRequestDto.getStartTime());
        show.setEndTime(showRequestDto.getEndTime());
        show.setFeatures(showRequestDto.getFeatures());
        show.setLanguages(showRequestDto.getLanguage());
        return show;
    }

    public static ShowResponseDto convert(Show show) {
        if (show == null)
            return null;
        ShowResponseDto showResponseDto = new ShowResponseDto();
        showResponseDto.setId(show.getId());
        showResponseDto.setStartTime(show.getStartTime());
        showResponseDto.setEndTime(show.getEndTime());
        showResponseDto.setFeatures(show.getFeatures());
        showResponseDto.setMovie(convert(show.getMovie()));
        showResponseDto.setAuditorium(convert(show.getAuditorium()));
        showResponseDto.setLanguage(show.getLanguages());
        if (show.getShowSeats() != null)
            showResponseDto.setShowSeats(show.getShowSeats().stream().map(EntityVOConverter::convert).collect(Collectors.toList()));
        if (show.getShowSeatTypes() != null) {
            showResponseDto.setShowSeatTypes(show.getShowSeatTypes().stream().map(x -> {
                ShowSeatTypeResponseDto showSeatTypeResponseDto = new ShowSeatTypeResponseDto();
                showSeatTypeResponseDto.setSeatType(x.getSeatType());
                showSeatTypeResponseDto.setPrice(x.getPrice());
                return showSeatTypeResponseDto;
            }).collect(Collectors.toList()));

        }
        return showResponseDto;
    }

    public static ShowSeatResponseDto convert(ShowSeat showSeat) {
        if (showSeat == null)
            return null;
        ShowSeatResponseDto showSeatResponseDto = new ShowSeatResponseDto();
        showSeatResponseDto.setId(showSeat.getId());
        showSeatResponseDto.setSeat(convert(showSeat.getSeat()));
        showSeatResponseDto.setState(showSeat.getState());
        return showSeatResponseDto;
    }

    public static TicketResponseDto convert(Ticket ticket){
        if(ticket==null) {
            return null;
        }
        TicketResponseDto ticketResponseDto =new TicketResponseDto();
        ticketResponseDto.setId(ticket.getId());
        ticketResponseDto.setTicketStatus(ticket.getTicketStatus());
        ticketResponseDto.setBookedBy(convert(ticket.getBookedBy()));
        ticketResponseDto.setTotalAmount(ticket.getTotalAmount());
        ticketResponseDto.setShow(convert(ticket.getShow()));
        return ticketResponseDto;
    }

    public static ShowSeat convert(ShowSeatRequestDto showSeatRequestDto) {
        if (showSeatRequestDto == null)
            return null;
        ShowSeat showSeat = new ShowSeat();
        Seat seat = new Seat();
        seat.setId(showSeatRequestDto.getSeatId());
        showSeat.setSeat(seat);
        Show show = new Show();
        show.setId(showSeatRequestDto.getShowId());
        showSeat.setShow(show);
        showSeat.setState(showSeat.getState());
        return showSeat;
    }
}
