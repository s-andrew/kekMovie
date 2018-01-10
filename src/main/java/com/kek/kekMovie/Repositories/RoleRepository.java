package com.kek.kekMovie.Repositories;


import com.kek.kekMovie.DTO.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long>{
    Role findByRoleIgnoreCase(String role);
}
