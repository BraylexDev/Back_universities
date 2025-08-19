package com.universities.universities.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.universities.universities.dto.CountryDTO;
import com.universities.universities.model.Country;
import com.universities.universities.model.University;
import com.universities.universities.service.CountryService;

@RestController
@RequestMapping("/country")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/active")
    public List<CountryDTO> loadActiveCountries(){
        List<Country> activeCountries = countryService.findByIsActive();
        return activeCountries.stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }

     //methods for convert entities or dto's
    private CountryDTO convertToDto(Country country){

        //CountryDTO countryDTO = modelMapper.map(country, CountryDTO.class);
        CountryDTO countryDTO = new CountryDTO();

        List<String> unisDTO = new ArrayList<>();
        List<University> uni= new ArrayList<>(country.getUniversities());

        unisDTO = uni.stream()
                    .map(university -> modelMapper.map(university.getName(), String.class))
                    .collect(Collectors.toList());

        countryDTO.setId_country(country.getId_country());
        countryDTO.setCode(country.getCode());
        countryDTO.setName(country.getName());
        countryDTO.setIsActive(country.getIsActive());
        countryDTO.setPhoneCode(country.getPhoneCode());
        countryDTO.setUniversities(unisDTO);

        
        return countryDTO;
    }

    
}

