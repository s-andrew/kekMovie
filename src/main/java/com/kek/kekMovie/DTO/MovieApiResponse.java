package com.kek.kekMovie.DTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieApiResponse {
    private MovieApiResponseItem characters;
    private MovieApiResponseItem genries;
    private MovieApiResponseItem keywords;
    private MovieApiResponseItem movies;
    private MovieApiResponseItem persons;
    private MovieApiResponseItem productionCompanies;


    public MovieApiResponse(){};

    public MovieApiResponseItem getCharacters() {
        return characters;
    }

    public void setCharacters(MovieApiResponseItem characters) {
        this.characters = characters;
    }

    public MovieApiResponseItem getGenries() {
        return genries;
    }

    public void setGenries(MovieApiResponseItem genries) {
        this.genries = genries;
    }

    public MovieApiResponseItem getKeywords() {
        return keywords;
    }

    public void setKeywords(MovieApiResponseItem keywords) {
        this.keywords = keywords;
    }

    public MovieApiResponseItem getMovies() {
        return movies;
    }

    public void setMovies(MovieApiResponseItem movies) {
        this.movies = movies;
    }

    public MovieApiResponseItem getPersons() {
        return persons;
    }

    public void setPersons(MovieApiResponseItem persons) {
        this.persons = persons;
    }

    public MovieApiResponseItem getProductionCompanies() {
        return productionCompanies;
    }

    public void setProductionCompanies(MovieApiResponseItem productionCompanies) {
        this.productionCompanies = productionCompanies;
    }
}
