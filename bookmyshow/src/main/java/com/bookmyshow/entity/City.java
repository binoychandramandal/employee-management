package com.bookmyshow.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@Entity
public class City extends BaseModel{
    private String name;
    @OneToMany( fetch = FetchType.EAGER, mappedBy = "city",cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private List<Theater> theaters;
}
