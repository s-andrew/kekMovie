package com.kek.kekMovie.Services.Implemaentations;

import com.kek.kekMovie.DTO.Movie;
import com.kek.kekMovie.DTO.Person;
import com.kek.kekMovie.DTO.ProductionCompany;
import com.kek.kekMovie.DTO.Character;
import com.kek.kekMovie.Repositories.*;
import com.kek.kekMovie.Services.MovieService;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private GenryRepository genryRepository;
    @Autowired
    private KeywordRepository keywordRepository;
    @Autowired
    private ProductionCompanyRepository productionCompanyRepository;
    @Autowired
    private CharacterRepository characterRepository;

    private static final int PAGE_SIZE = 10;

    public Map<String, Set<Pair<Long, String>>> globalSearch(String string){
        Map<String, Set<Pair<Long, String>>> result = new HashMap<>();

        Set<Pair<Long, String>> characters = StreamSupport
                .stream(characterRepository.findByNameContaining(string).spliterator(), true)
                        .map((x) -> new Pair<>(x.getId(), x.getNameWithMovie()))
                        .collect(Collectors.toSet());
        Set<Pair<Long, String>> genries = StreamSupport
                .stream(genryRepository.findByValueContaining(string).spliterator(), true)
                        .map((x) -> new Pair<>(x.getId(), x.getValue()))
                        .collect(Collectors.toSet());
        Set<Pair<Long, String>> keywords = StreamSupport
                .stream(keywordRepository.findByValueContaining(string).spliterator(), true)
                        .map((x) -> new Pair<>(x.getId(), x.getValue()))
                        .collect(Collectors.toSet());
        Set<Pair<Long, String>> movies = StreamSupport
                .stream(movieRepository.findByTitleContainingOrOriginalTitleContaining(string, string).spliterator(), true)
                        .map((x) -> new Pair<>(x.getId(), x.getTitle()))
                        .collect(Collectors.toSet());
        Set<Pair<Long, String>> persons = StreamSupport
                .stream(personRepository.findByNameContaining(string).spliterator(), true)
                        .map((x) -> new Pair<>(x.getId(), x.getName()))
                        .collect(Collectors.toSet());
        Set<Pair<Long, String>> productionCompanies = StreamSupport
                .stream(productionCompanyRepository.findByNameContaining(string).spliterator(), true)
                        .map((x) -> new Pair<>(x.getId(), x.getName()))
                        .collect(Collectors.toSet());
        if (!characters.isEmpty())
            result.put("movies_by_character", characters);
        if (!genries.isEmpty())
            result.put("movies_by_genry", genries);
        if (!keywords.isEmpty())
            result.put("movies_by_keyword", keywords);
        if (!movies.isEmpty())
            result.put("movies", movies);
        if (!persons.isEmpty())
            result.put("movies_by_person", persons);
        if (!productionCompanies.isEmpty())
            result.put("movies_by_production_company", productionCompanies);
        return result;
    }


    public Movie getMovie(long id){return movieRepository.findOne(id);}


    @Transactional
    public Iterable<Movie> getMovies(Integer pageNumber){
        PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE, Sort.Direction.ASC, "id");
        return movieRepository.findAll(request).getContent();
    }

    public Movie getMoviesByCharacter(long id){
        return characterRepository.findOne(id).getMovie();
    }

    private Pair<Long, String> getMovieShortInfo(Movie movie){
        String title;
        if (!movie.getTitle().equals(movie.getOriginalTitle()))
            title = movie.getTitle() + " (" + movie.getOriginalTitle() + ")";
        else
            title = movie.getTitle();
        return new Pair<>(movie.getId(), title);
    }

    public Iterable<Pair<Long, String>> getMoviesByGenry(long id){
        return genryRepository.findOne(id).getMovies().stream()
                .map(movie -> getMovieShortInfo(movie))
                .collect(Collectors.toSet());
    }

    public Iterable<Pair<Long, String>> getMoviesByKeyword(long id){
        return keywordRepository.findOne(id).getMovies().stream()
                .map(movie -> getMovieShortInfo(movie))
                .collect(Collectors.toSet());
    }

    public Map<String, Set<Pair<Long, String>>> getMoviesByPerson(long id){
        Person person = personRepository.findOne(id);
        Map<String, Set<Pair<Long, String>>> result = new HashMap<>();
        result.put("Director",person.getMovies().stream()
                .map(movie -> getMovieShortInfo(movie))
                .collect(Collectors.toSet()));
        result.put("Actor", person.getCharacters().stream()
                .map(character -> character.getMovie())
                .map(movie -> getMovieShortInfo(movie))
                .collect(Collectors.toSet()));
        return result;
    }

    public Iterable<Pair<Long, String>> getMoviesByProductionCompany(long id){
        return productionCompanyRepository.findOne(id).getMovies().stream()
                .map(movie -> getMovieShortInfo(movie))
                .collect(Collectors.toSet());
    }
}

