package com.kek.kekMovie.Services;

import com.kek.kekMovie.DTO.MovieApiResponse;
import com.kek.kekMovie.Entities.Movie;
import javafx.util.Pair;

import java.util.Map;
import java.util.Set;

public interface MovieService {
    MovieApiResponse globalSearch(String string);
    Movie getMovie(long id);
    Iterable<Movie> getMovies(Integer pageNumber);

//    Movie getMoviesByCharacter(long id);
    MovieApiResponse getMoviesByGenry(long id);
    MovieApiResponse getMoviesByKeyword(long id);
    MovieApiResponse getMoviesByPerson(long id);
    MovieApiResponse getMoviesByProductionCompany(long id);
}
