package com.bookmyshow.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Ticket extends BaseModel {
    @ManyToOne
    private User bookedBy;
    @ManyToOne
    private Show show;
    @ManyToMany
    private List<ShowSeat> showSeats;
    private double totalAmount;
    @Enumerated(EnumType.ORDINAL)
    private TicketStatus ticketStatus;
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeOfBooking=new Date();
    @OneToMany(mappedBy = "ticket")
    private List<Payment> payments;
}
