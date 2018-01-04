package com.kek.kekMovie.Repositories;

import com.kek.kekMovie.DTO.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Long>{
    Iterable<Movie> findByTitleContainingOrOriginalTitleContaining(String title, String originalTitle);

}
