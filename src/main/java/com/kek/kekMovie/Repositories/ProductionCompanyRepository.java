package com.kek.kekMovie.Repositories;

import com.kek.kekMovie.Entities.ProductionCompany;
import org.springframework.data.repository.CrudRepository;

public interface ProductionCompanyRepository extends CrudRepository<ProductionCompany, Long>{
    Iterable<ProductionCompany> findByNameContaining(String companyName);
}
