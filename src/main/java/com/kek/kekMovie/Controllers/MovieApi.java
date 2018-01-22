package com.kek.kekMovie.Controllers;

import com.kek.kekMovie.DTO.CharacterShortInfo;
import com.kek.kekMovie.DTO.MovieApiResponse;
import com.kek.kekMovie.Entities.Character;
import com.kek.kekMovie.Entities.Movie;
import com.kek.kekMovie.Repositories.CharacterRepository;
import com.kek.kekMovie.Services.MovieService;
import javafx.util.Pair;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/movie_api")
@PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
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
    public MovieApiResponse globalSearch(@ModelAttribute(value = "f") String find){
        if (!find.equals(""))
            return movieService.globalSearch(find);
        else
            return null;
    }

    @GetMapping("movies_by_genry/{id}")
    public MovieApiResponse getMovieByGenry(@PathVariable(value = "id") Long id){
        return movieService.getMoviesByGenry(id);
    }

    @GetMapping("movies_by_keyword/{id}")
    public MovieApiResponse getMoviesByKeyword(@PathVariable(value = "id") Long id){
        return movieService.getMoviesByKeyword(id);
    }

    @GetMapping("movies_by_person/{id}")
    public MovieApiResponse getMoviesByPerson(@PathVariable(value = "id") Long id){
        return movieService.getMoviesByPerson(id);
    }

    @GetMapping("movies_by_production_company/{id}")
    public MovieApiResponse getMoviesByProductionCompany(@PathVariable(value = "id") Long id){
        return movieService.getMoviesByProductionCompany(id);
    }
}
