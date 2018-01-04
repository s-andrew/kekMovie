package com.kek.kekMovie.DTO;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
@Table(name = "characters")
public class Character {

    @Id
    @GeneratedValue
    private long id;

    private String name;
    @Column(name = "order_")
    private int order;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    @JsonBackReference
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "person_id")
    @JsonManagedReference
    private Person actor;

    protected Character() {}

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @JsonIgnore
    public String getNameWithMovie(){
        return name + " (" + movie.getTitle() + ")";
    }

    public int getOrder() {
        return order;
    }

    public Movie getMovie() { return movie; }
}
