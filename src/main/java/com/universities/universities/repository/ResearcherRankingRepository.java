package com.universities.universities.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.universities.universities.model.ResearcherRanking;

@Repository
public interface ResearcherRankingRepository extends JpaRepository<ResearcherRanking, Long> {
    
    // Delete all records for a specific year
    void deleteByYear(Integer year);
    
    // Check if data exists for a specific year
    boolean existsByYear(Integer year);
    
    // Get all years with data
    @Query("SELECT DISTINCT r.year FROM ResearcherRanking r ORDER BY r.year DESC")
    List<Integer> findDistinctYears();
    
    // Get all categories for a specific year
    @Query("SELECT DISTINCT r.category FROM ResearcherRanking r WHERE r.year = :year AND r.category IS NOT NULL ORDER BY r.category")
    List<String> findDistinctCategoriesByYear(@Param("year") Integer year);
    
    // Get all subcategories for a specific year and category
    @Query("SELECT DISTINCT r.subcategory FROM ResearcherRanking r WHERE r.year = :year AND r.category = :category AND r.subcategory IS NOT NULL ORDER BY r.subcategory")
    List<String> findDistinctSubcategoriesByYearAndCategory(@Param("year") Integer year, @Param("category") String category);
    
    // Get all subcategory2 for a specific year, category and subcategory
    @Query("SELECT DISTINCT r.subcategory2 FROM ResearcherRanking r WHERE r.year = :year AND r.category = :category AND r.subcategory = :subcategory AND r.subcategory2 IS NOT NULL ORDER BY r.subcategory2")
    List<String> findDistinctSubcategory2ByYearAndCategoryAndSubcategory(@Param("year") Integer year, @Param("category") String category, @Param("subcategory") String subcategory);
    
    // Get all countries for a specific year
    @Query("SELECT DISTINCT r.codeWorking FROM ResearcherRanking r WHERE r.year = :year AND r.codeWorking IS NOT NULL ORDER BY r.codeWorking")
    List<String> findDistinctCountriesByYear(@Param("year") Integer year);
    
    // Get all universities for a specific year
    @Query("SELECT DISTINCT r.university FROM ResearcherRanking r WHERE r.year = :year AND r.university IS NOT NULL ORDER BY r.university")
    List<String> findDistinctUniversitiesByYear(@Param("year") Integer year);
    
    // Find with filters
    @Query("SELECT r FROM ResearcherRanking r WHERE " +
            "(:year IS NULL OR r.year = :year) AND " +
            "(:category IS NULL OR r.category = :category) AND " +
            "(:subcategory IS NULL OR r.subcategory = :subcategory) AND " +
            "(:subcategory2 IS NULL OR r.subcategory2 = :subcategory2) AND " +
            "(:country IS NULL OR r.codeWorking = :country) AND " +
            "(:institution IS NULL OR r.university LIKE %:institution%) " +
            "ORDER BY r.position ASC")
    Page<ResearcherRanking> findWithFilters(
        @Param("year") Integer year,
        @Param("category") String category,
        @Param("subcategory") String subcategory,
        @Param("subcategory2") String subcategory2,
        @Param("country") String country,
        @Param("institution") String institution,
        Pageable pageable
    );
}