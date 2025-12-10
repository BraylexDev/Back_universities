package com.universities.universities.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.universities.universities.dto.PaginatedRankingResponseDto;
import com.universities.universities.dto.RankingFiltersDto;
import com.universities.universities.dto.ResearcherRankingResponseDto;
import com.universities.universities.model.ResearcherRanking;
import com.universities.universities.repository.ResearcherRankingRepository;

@Service
public class RankingService {

    private final ResearcherRankingRepository repository;

    public RankingService(ResearcherRankingRepository repository) {
        this.repository = repository;
    }
    
    public PaginatedRankingResponseDto getRankings(
            Integer year,
            String category,
            String subcategory,
            String subcategory2,
            String country,
            String institution,
            int page,
            int size) {
        
        Pageable pageable = PageRequest.of(page, size);
        
        Page<ResearcherRanking> rankingsPage = repository.findWithFilters(
            year, category, subcategory, subcategory2, country, institution, pageable
        );
        
        List<ResearcherRankingResponseDto> content = rankingsPage.getContent()
            .stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
        
        return new PaginatedRankingResponseDto(
            content,
            rankingsPage.getNumber(),
            rankingsPage.getSize(),
            rankingsPage.getTotalElements(),
            rankingsPage.getTotalPages(),
            rankingsPage.isFirst(),
            rankingsPage.isLast()
        );
    }
    
    public RankingFiltersDto getAvailableFilters(Integer year) {
        List<Integer> years = repository.findDistinctYears();
        
        // If no year is specified, return only years
        if (year == null) {
            return new RankingFiltersDto(
                years,
                List.of(),
                List.of(),
                List.of(),
                List.of(),
                List.of()
            );
        }
        
        List<String> categories = repository.findDistinctCategoriesByYear(year);
        List<String> countries = repository.findDistinctCountriesByYear(year);
        List<String> institutions = repository.findDistinctUniversitiesByYear(year);
        
        return new RankingFiltersDto(
            years,
            categories,
            List.of(), 
            List.of(), 
            countries,
            institutions
        );
    }
    
    public List<String> getSubcategories(Integer year, String category) {
        if (year == null || category == null) {
            return List.of();
        }
        return repository.findDistinctSubcategoriesByYearAndCategory(year, category);
    }
    
    public List<String> getSubcategory2List(Integer year, String category, String subcategory) {
        if (year == null || category == null || subcategory == null) {
            return List.of();
        }
        return repository.findDistinctSubcategory2ByYearAndCategoryAndSubcategory(year, category, subcategory);
    }
    
    public List<Integer> getAvailableYears() {
        return repository.findDistinctYears();
    }
    
    public boolean hasDataForYear(Integer year) {
        return repository.existsByYear(year);
    }
    
    public long getTotalRecordsForYear(Integer year) {
        return repository.count();
    }
    
    private ResearcherRankingResponseDto convertToDto(ResearcherRanking ranking) {
        return new ResearcherRankingResponseDto(
            ranking.getId(),
            ranking.getYear(),
            ranking.getPosition(),
            ranking.getName(),
            ranking.getScore(),
            ranking.getPosition2(),
            ranking.getCategory(),
            ranking.getSubcategory(),
            ranking.getSubcategory2(),
            ranking.getUniversity(),
            ranking.getCodeCountry(),
            ranking.getCodeWorking(),
            ranking.getProfile()
        );
    }

}
