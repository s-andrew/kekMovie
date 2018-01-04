package com.kek.kekMovie.DTO;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "genries")
public class Genry {

    @Id
    @GeneratedValue
    private Long id;

    private String value;

    @ManyToMany(mappedBy = "genries")
    @JsonBackReference
    private Set<Movie> movies;

    protected Genry() {}

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
