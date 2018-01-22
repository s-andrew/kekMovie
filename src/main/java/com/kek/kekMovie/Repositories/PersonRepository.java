package com.kek.kekMovie.Repositories;

import com.kek.kekMovie.Entities.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long>{
    Iterable<Person> findByNameContaining(String name);
}
