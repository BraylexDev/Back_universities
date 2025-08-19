package com.universities.universities.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.universities.universities.model.Country;
import com.universities.universities.repository.CountryRepository;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    public List<Country> findByIsActive(){
        return countryRepository.findByIsActive(1);
    }

    /* public Country getCountryById(Long id){
        return countryRepository.findById_country(id);
    }
    public String getNameCountry(Long id){
        Country country = countryRepository.findById_country(id);
        return country.getName();
    } */
    
}
