package com.kek.kekMovie.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "keywords")
public class Keyword {

    @Id
    @GeneratedValue
    private Long id;

    private String value;

    @ManyToMany(mappedBy = "keywords")
    @JsonBackReference
    private Set<Movie> movies;

    protected Keyword(){}

    public Long getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public Set<Movie> getMovies() {
        return movies;
    }
}