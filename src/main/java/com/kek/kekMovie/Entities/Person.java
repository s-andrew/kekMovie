package com.kek.kekMovie.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "persons")
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "directors")
    @JsonBackReference
    private List<Movie> movies;

    @OneToMany(mappedBy = "actor")
    @JsonBackReference
    private List<Character> characters;

    public Person(){}

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public List<Character> getCharacters() { return characters; }
}
