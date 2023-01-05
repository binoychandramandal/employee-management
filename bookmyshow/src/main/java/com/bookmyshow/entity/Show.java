package com.bookmyshow.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "shows")
public class Show extends BaseModel {
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime startTime;
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime endTime;
    @ManyToOne
    private Movie movie;
    @ManyToOne
    private Auditorium auditorium;
    @ElementCollection
    @Enumerated(EnumType.ORDINAL)
    private List<Feature> features;
    @ElementCollection
    @Enumerated(EnumType.ORDINAL)
    private List<Language> languages;
    @OneToMany(mappedBy = "show")
    private List<ShowSeat> showSeats;
    @OneToMany(mappedBy = "show")
    private List<ShowSeatType> showSeatTypes;



}
