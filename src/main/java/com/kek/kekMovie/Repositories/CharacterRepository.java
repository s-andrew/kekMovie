package com.kek.kekMovie.Repositories;

import com.kek.kekMovie.DTO.Character;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends CrudRepository<Character, Long>{
    Iterable<Character> findByNameContaining(String name);
}
