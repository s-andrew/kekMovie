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


    @GetMapping("/global_search/{string}")
    public Map<String, Set<Pair<Long, String>>> test(@PathVariable(value = "string") String string){
        return movieService.globalSearch(string);
    }
}
