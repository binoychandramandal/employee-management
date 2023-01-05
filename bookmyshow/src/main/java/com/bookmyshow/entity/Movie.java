package com.bookmyshow.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
public class Movie extends BaseModel {
    private String name;
    @ManyToMany
    private List<Actor> actors;
    @ElementCollection
    @Enumerated(EnumType.ORDINAL)
    private List<Feature> features;
    @ElementCollection
    @Enumerated(EnumType.ORDINAL)
    private List<Genre> genres;
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Language> languages;

}
