package com.kek.kekMovie.Repositories;

import com.kek.kekMovie.Entities.Keyword;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeywordRepository extends CrudRepository<Keyword, Long>{
    Iterable<Keyword> findByValueContaining(String keyword);
}
