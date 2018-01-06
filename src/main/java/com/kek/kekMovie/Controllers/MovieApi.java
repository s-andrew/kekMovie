package com.kek.kekMovie.Controllers;

import com.kek.kekMovie.DTO.Movie;
import com.kek.kekMovie.Services.MovieService;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/movie_api")
public class MovieApi {

    @Autowired
    private MovieService movieService;

    @GetMapping("/movies/{id}")
    public Movie getMovie(@PathVariable(value = "id") Long id){
        return movieService.getMovie(id);
    }


    @GetMapping("/movies")
    public Iterable<Movie> getMovies(@ModelAttribute(value = "p")int page){
        return movieService.getMovies(page);
    }


    @GetMapping("/global_search")
    public Map<String, Set<Pair<Long, String>>> test(@ModelAttribute(value = "f") String find){
        return movieService.globalSearch(find);
    }

    @GetMapping("/movies_by_character/{id}")
    public Movie getMoviesByCharacter(@PathVariable(value = "id") Long id){
        return movieService.getMoviesByCharacter(id);
    }

    @GetMapping("movies_by_genry/{id}")
    public Iterable<Pair<Long, String>> getMovieByGenry(@PathVariable(value = "id") Long id){
        return movieService.getMoviesByGenry(id);
    }

    @GetMapping("movies_by_keyword/{id}")
    public Iterable<Pair<Long, String>> getMoviesByKeyword(@PathVariable(value = "id") Long id){
        return movieService.getMoviesByKeyword(id);
    }

    @GetMapping("movies_by_person/{id}")
    public Map<String, Set<Pair<Long, String>>> getMoviesByPerson(@PathVariable(value = "id") Long id){
        return movieService.getMoviesByPerson(id);
    }

    @GetMapping("movies_by_production_company/{id}")
    public Iterable<Pair<Long, String>> getMoviesByProductionCompany(@PathVariable(value = "id") Long id){
        return movieService.getMoviesByProductionCompany(id);
    }
}
