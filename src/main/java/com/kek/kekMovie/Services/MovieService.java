package com.kek.kekMovie.Services;

import com.kek.kekMovie.DTO.Movie;
import com.kek.kekMovie.DTO.Person;
import com.kek.kekMovie.DTO.ProductionCompany;
import com.kek.kekMovie.DTO.Character;
import javafx.util.Pair;

import java.util.Map;
import java.util.Set;

public interface MovieService {
    Map<String, Set<Pair<Long, String>>> globalSearch(String string);
    Movie getMovie(long id);
    Iterable<Movie> getMovies(Integer pageNumber);
}
