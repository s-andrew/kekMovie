package com.kek.kekMovie.Repositories;

import com.kek.kekMovie.DTO.Keyword;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeywordRepository extends CrudRepository<Keyword, Long>{
    Iterable<Keyword> findByValueContaining(String keyword);
}
