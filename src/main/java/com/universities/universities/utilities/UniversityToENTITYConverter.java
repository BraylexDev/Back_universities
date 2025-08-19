package com.universities.universities.utilities;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import com.universities.universities.dto.UniversityDTO;
import com.universities.universities.model.Country;
import com.universities.universities.model.Ranking;
import com.universities.universities.model.University;

public class UniversityToENTITYConverter implements Converter<UniversityDTO, University>{

    @Override
    public University convert(MappingContext<UniversityDTO, University> context) {
        UniversityDTO source = context.getSource();
        University destination = context.getDestination();

        if(destination == null){
            destination = new University();
        }

        destination.setId_university(source.getId());
        destination.setAddress(source.getAddress());
        Country country = new Country();
        country.setName(source.getCountry());
        destination.setDescription(source.getDescription());
        destination.setFoundYear(source.getFoundYear());
        destination.setName(source.getName());
        destination.setRegion(source.getRegion());
        destination.setWebSite(source.getWebSite());
        
        Set<Ranking> rankings = new HashSet<>();
        Map<Integer, Integer> rankingForYear = source.getRankings();
        for (Integer r : rankingForYear.keySet()) {
            Ranking rankingAux = new Ranking();
            rankingAux.setYear(r);
            rankingAux.setPosition(rankingForYear.get(r));
            rankings.add(rankingAux);
        }
        destination.setRankings(rankings);

        return destination;
    }
}
