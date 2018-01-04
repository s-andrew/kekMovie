package com.kek.kekMovie.Repositories;

import com.kek.kekMovie.DTO.ProductionCompany;
import org.springframework.data.repository.CrudRepository;

public interface ProductionCompanyRepository extends CrudRepository<ProductionCompany, Long>{
    Iterable<ProductionCompany> findByNameContaining(String companyName);
}
