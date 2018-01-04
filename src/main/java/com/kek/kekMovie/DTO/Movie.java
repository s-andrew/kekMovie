package com.kek.kekMovie.DTO;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String originalTitle;
    private String tagline;
    private String overview;
    private Integer runtime;
    private Long budget;
    private Long revenue;
    private Date releaseDate;
    private String originalLanguage;
    private Integer voteAverage;

    @OneToMany(mappedBy = "movie")
    @JsonManagedReference
    private List<Character> characters;

    @ManyToMany
    @JoinTable(name = "relMovieDirector",
            joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "person_id", referencedColumnName = "id"))
    @JsonManagedReference
    private List<Person> directors;

    @ManyToMany
    @JoinTable(name = "relMovieGenry",
            joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "genry_id", referencedColumnName = "id"))
    @JsonManagedReference
    private List<Genry> genries;

    @ManyToMany
    @JoinTable(name = "relMovieKeyword",
            joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "keyword_id", referencedColumnName = "id"))
    @JsonManagedReference
    private List<Keyword> keywords;

    @ManyToMany
    @JoinTable(name = "relMovieProductionCompany",
            joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "production_company_id", referencedColumnName = "id"))
    @JsonManagedReference
    private List<ProductionCompany> productionCompanies;

    protected Movie(){}

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getTagline() {
        return tagline;
    }

    public String getOverview() {
        return overview;
    }

    public int getRuntime() {
        return runtime;
    }

    public long getBudget() {
        return budget;
    }

    public long getRevenue() {
        return revenue;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public int getVoteAverage() {
        return voteAverage;
    }

    public List<Person> getDirectors() {
        return directors;
    }

    public List<Genry> getGenries() {
        return genries;
    }

    public List<Keyword> getKeywords() {
        return keywords;
    }

    public List<ProductionCompany> getProductionCompanies() {
        return productionCompanies;
    }

}