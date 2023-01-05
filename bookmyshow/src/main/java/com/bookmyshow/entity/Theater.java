package com.bookmyshow.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Theater extends BaseModel{
    private String name;
    private String address;
    @OneToMany(mappedBy = "theater",cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private List<Auditorium> auditoriums;
    @ManyToOne(fetch = FetchType.LAZY)
    private City city;

    @Override
    public String toString() {
        return "Theater{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
