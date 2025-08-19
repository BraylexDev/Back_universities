package com.universities.universities.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.universities.universities.model.Country;
import java.util.List;


public interface CountryRepository extends JpaRepository<Country, Long>{

    public List<Country> findByIsActive(int isActive);
}
