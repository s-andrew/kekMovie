package com.kek.kekMovie.Services.Implemaentations;

import com.kek.kekMovie.DTO.Movie;
import com.kek.kekMovie.DTO.Person;
import com.kek.kekMovie.DTO.ProductionCompany;
import com.kek.kekMovie.DTO.Character;
import com.kek.kekMovie.Repositories.*;
import com.kek.kekMovie.Services.MovieService;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Map<String, Set<Pair<Long, String>>> globalSearch(String string){
        Map<String, Set<Pair<Long, String>>> result = new HashMap<>();

        Set<Pair<Long, String>> characters = StreamSupport.stream(characterRepository.findByNameContaining(string).spliterator(), true)
                .map((x) -> new Pair<>(x.getId(), x.getNameWithMovie()))
                .collect(Collectors.toSet());
        Set<Pair<Long, String>> genries = StreamSupport.stream(genryRepository.findByValueContaining(string).spliterator(), true)
                .map((x) -> new Pair<>(x.getId(), x.getValue()))
                .collect(Collectors.toSet());
        Set<Pair<Long, String>> keywords = StreamSupport.stream(keywordRepository.findByValueContaining(string).spliterator(), true)
                .map((x) -> new Pair<>(x.getId(), x.getValue()))
                .collect(Collectors.toSet());
        Set<Pair<Long, String>> movies = StreamSupport.stream(movieRepository.findByTitleContainingOrOriginalTitleContaining(string, string).spliterator(), true)
                .map((x) -> new Pair<>(x.getId(), x.getTitle()))
                .collect(Collectors.toSet());
        Set<Pair<Long, String>> persons = StreamSupport.stream(personRepository.findByNameContaining(string).spliterator(), true)
                .map((x) -> new Pair<>(x.getId(), x.getName()))
                .collect(Collectors.toSet());
        Set<Pair<Long, String>> productionCompanies = StreamSupport.stream(productionCompanyRepository.findByNameContaining(string).spliterator(), true)
                .map((x) -> new Pair<>(x.getId(), x.getName()))
                .collect(Collectors.toSet());
        result.put("characters", characters);
        result.put("genries", genries);
        result.put("keywords", keywords);
        result.put("movies", movies);
        result.put("persons", persons);
        result.put("productionCompanies", productionCompanies);
        return result;
    }


    public Iterable<Movie> getMovieByTitle(String title){ return movieRepository.findByTitleContainingOrOriginalTitleContaining(title, title); }
    public Iterable<Movie> getAllMovies(){ return  movieRepository.findAll(); }
    public Character getCharacter(long id){return characterRepository.findOne(id);}
    public Movie getMovie(long id){return movieRepository.findOne(id);}
    public Person getPerson(long id){return personRepository.findOne(id);}
    public ProductionCompany getProductionCompany(long id){return productionCompanyRepository.findOne(id);}

}
