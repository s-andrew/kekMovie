package com.kek.kekMovie.Services;

import com.kek.kekMovie.DTO.Movie;
import com.kek.kekMovie.DTO.Person;
import com.kek.kekMovie.DTO.ProductionCompany;
import com.kek.kekMovie.DTO.Character;
import javafx.util.Pair;

import java.util.Map;
import java.util.Set;

public interface movieService {
    // Find anything
    Map<String, Set<Pair<Long, String>>> globalSearch(String string);


    Character getCharacter(long id);
    Person getPerson(long id);
    ProductionCompany getProductionCompany(long id);


    // Find movie methods
    Movie getMovie(long id);
    Iterable<Movie> getMovieByTitle(String title);
    Iterable<Movie> getAllMovies();
//    Iterable<Movie> findMovieByDirector(String directorName);
//    Iterable<Movie> findMovieByActor(String actorName);
//    Iterable<Movie> findMovieByAnything(String anything);

}
