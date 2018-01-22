package com.kek.kekMovie.Repositories;

import com.kek.kekMovie.Entities.Genry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenryRepository extends CrudRepository<Genry, Long>{
    Iterable<Genry> findByValueContaining(String genry);
}
