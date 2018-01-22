package com.kek.kekMovie.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "production_companies")
public class ProductionCompany {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "productionCompanies")
    @JsonBackReference
    private Set<Movie> movies;

    protected ProductionCompany(){}

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Movie> getMovies() {
        return movies;
    }
}

