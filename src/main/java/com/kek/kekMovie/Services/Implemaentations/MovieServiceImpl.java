package com.kek.kekMovie.Services.Implemaentations;

import com.kek.kekMovie.DTO.CharacterShortInfo;
import com.kek.kekMovie.DTO.MovieApiResponse;
import com.kek.kekMovie.DTO.MovieApiResponseItem;
import com.kek.kekMovie.DTO.MovieShortInfo;
import com.kek.kekMovie.Entities.*;
import com.kek.kekMovie.Repositories.*;
import com.kek.kekMovie.Services.MovieService;
import javafx.util.Pair;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
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
    @Autowired
    private ModelMapper modelMapper;

    private static final int PAGE_SIZE = 10;

    public MovieApiResponse globalSearch(String query){
        List<Object> movies = StreamSupport
                .stream(movieRepository.findByTitleContainingOrOriginalTitleContaining(query, query).spliterator(), true)
                .map(movie -> modelMapper.map(movie, MovieShortInfo.class))
                .collect(Collectors.toList());

        List<Object> characters = StreamSupport
                .stream(characterRepository.findByNameContaining(query).spliterator(), true)
                .map(character -> modelMapper.map(character, CharacterShortInfo.class))
                .collect(Collectors.toList());

        List<Object> persons = StreamSupport
                .stream(personRepository.findByNameContaining(query).spliterator(), true)
                .collect(Collectors.toList());

        List<Object> genries = StreamSupport
                .stream(genryRepository.findByValueContaining(query).spliterator(), true)
                .collect(Collectors.toList());

        List<Object> keywords = StreamSupport
                .stream(keywordRepository.findByValueContaining(query).spliterator(), true)
                .collect(Collectors.toList());

        List<Object> productionCompanies = StreamSupport
                .stream(productionCompanyRepository.findByNameContaining(query).spliterator(), true)
                .collect(Collectors.toList());

        MovieApiResponse response = new MovieApiResponse();
        response.setCharacters(new MovieApiResponseItem("Characters", characters));
        response.setGenries(new MovieApiResponseItem("Genries", genries));
        response.setKeywords(new MovieApiResponseItem("Keywords", keywords));
        response.setMovies(new MovieApiResponseItem("Movies", movies));
        response.setPersons(new MovieApiResponseItem("Persons", persons));
        response.setProductionCompanies(new MovieApiResponseItem("Production companies", productionCompanies));
        return response;
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


    private MovieApiResponse createOnlyMoviesResponse(String title, Set<Movie> movies){
        List<Object> moviesList = movies.stream()
                .map(movie -> modelMapper.map(movie, MovieShortInfo.class))
                .collect(Collectors.toList());
        MovieApiResponse response = new MovieApiResponse();
        response.setMovies(new MovieApiResponseItem(title, moviesList));
        return response;
    }

    public MovieApiResponse getMoviesByGenry(long id){
        Genry genry = genryRepository.findOne(id);
        return createOnlyMoviesResponse("Movies of the genre ".concat(genry.getValue()), genry.getMovies());
    }

    public MovieApiResponse getMoviesByKeyword(long id){
        Keyword keyword = keywordRepository.findOne(id);
        return createOnlyMoviesResponse("Movies by keyword ".concat(keyword.getValue()), keyword.getMovies());
    }

    public MovieApiResponse getMoviesByProductionCompany(long id){
        ProductionCompany productionCompany = productionCompanyRepository.findOne(id);
        return createOnlyMoviesResponse("Movies released by ".concat(productionCompany.getName()), productionCompany.getMovies());
    }


    public MovieApiResponse getMoviesByPerson(long id){
        Person person = personRepository.findOne(id);
        List<Object> characters = person.getCharacters().stream()
                .map(character -> modelMapper.map(character, CharacterShortInfo.class))
                .collect(Collectors.toList());
        List<Object> movies = person.getMovies().stream()
                .map(movie -> modelMapper.map(movie, MovieShortInfo.class))
                .collect(Collectors.toList());
        MovieApiResponse response = new MovieApiResponse();
        response.setCharacters(new MovieApiResponseItem("Cast in movies", characters));
        response.setMovies(new MovieApiResponseItem("Directed movies", movies));
        return response;
    }

}

