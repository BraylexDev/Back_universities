package com.universities.universities.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.universities.universities.dto.ExcelUploadResponseDto;
import com.universities.universities.dto.PaginatedRankingResponseDto;
import com.universities.universities.dto.RankingFiltersDto;
import com.universities.universities.service.ExcelProcessingService;
import com.universities.universities.service.RankingService;

@RestController
@RequestMapping("/api/ranking")
public class RankingController {    
    private final ExcelProcessingService excelProcessingService;
    private final RankingService rankingService;
    
    public RankingController(ExcelProcessingService excelProcessingService, RankingService rankingService) {
        this.excelProcessingService = excelProcessingService;
        this.rankingService = rankingService;
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ExcelUploadResponseDto> uploadRankingFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("year") Integer year) {
        ExcelUploadResponseDto response = excelProcessingService.processExcelFile(file, year);
        

        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    @GetMapping
    public ResponseEntity<PaginatedRankingResponseDto> getRankings(
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String subcategory,
            @RequestParam(required = false) String subcategory2,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String institution,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "1000") int size) {

        PaginatedRankingResponseDto response = rankingService.getRankings(
            year, category, subcategory, subcategory2, country, institution, page, size
        );
        
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/filters")
    public ResponseEntity<RankingFiltersDto> getFilters(
            @RequestParam(required = false) Integer year) {
        
        RankingFiltersDto filters = rankingService.getAvailableFilters(year);
        return ResponseEntity.ok(filters);
    }
    
    @GetMapping("/subcategories")
    public ResponseEntity<List<String>> getSubcategories(
            @RequestParam Integer year,
            @RequestParam String category) {
        
        List<String> subcategories = rankingService.getSubcategories(year, category);
        return ResponseEntity.ok(subcategories);
    }
    
    @GetMapping("/subcategory2")
    public ResponseEntity<List<String>> getSubcategory2List(
            @RequestParam Integer year,
            @RequestParam String category,
            @RequestParam String subcategory) {
        
        List<String> subcategory2List = rankingService.getSubcategory2List(year, category, subcategory);
        return ResponseEntity.ok(subcategory2List);
    }
    
    @GetMapping("/years")
    public ResponseEntity<List<Integer>> getAvailableYears() {
        List<Integer> years = rankingService.getAvailableYears();
        return ResponseEntity.ok(years);
    }
    
    @GetMapping("/years/{year}/exists")
    public ResponseEntity<Boolean> checkDataExistsForYear(
            @PathVariable Integer year) {
        
        boolean exists = rankingService.hasDataForYear(year);
        return ResponseEntity.ok(exists);
    }
}
