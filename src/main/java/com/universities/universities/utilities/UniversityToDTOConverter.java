package com.universities.universities.utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import com.universities.universities.dto.UniversityDTO;
import com.universities.universities.model.Country;
import com.universities.universities.model.Ranking;
import com.universities.universities.model.University;

public class UniversityToDTOConverter implements Converter<University, UniversityDTO>{

    @Override
    public UniversityDTO convert(MappingContext<University, UniversityDTO> context) {
        University source = context.getSource();
        UniversityDTO destination = context.getDestination();

        if(destination == null){
            destination = new UniversityDTO();
        }

        destination.setId(source.getId_university());
        destination.setAddress(source.getAddress());
        Country country = source.getCountry();
        if(country != null){
            destination.setCountry(country.getName());
        }
        destination.setDescription(source.getDescription());
        destination.setFoundYear(source.getFoundYear());
        destination.setName(source.getName());
        destination.setRegion(source.getRegion());
        destination.setWebSite(source.getWebSite());
        HashMap<Integer, Integer> ranksYearAndPosition = new HashMap<>();
        List<Ranking> ranks = new ArrayList<>(source.getRankings());
        if(!ranks.isEmpty()){
            for (Ranking ranking : ranks) {
                ranksYearAndPosition.put(ranking.getYear(), ranking.getPosition());
            }
            destination.setRankings(ranksYearAndPosition);
        }

        return destination;
    }
}
