package com.universities.universities.service;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.universities.universities.dto.ExcelRowDto;
import com.universities.universities.dto.ExcelUploadResponseDto;
import com.universities.universities.model.ResearcherRanking;
import com.universities.universities.repository.ResearcherRankingRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ExcelProcessingService {

    private final ResearcherRankingRepository repository;
    

    public ExcelProcessingService(ResearcherRankingRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public ExcelUploadResponseDto processExcelFile(MultipartFile file, Integer year) {
        List<String> errors = new ArrayList<>();
        
        try {
            // Validate file
            if (file.isEmpty()) {
                return new ExcelUploadResponseDto(false, "File is empty", year, 0, List.of("File is empty"));
            }
            
            if (!isValidExcelFile(file)) {
                return new ExcelUploadResponseDto(false, "Invalid file format. Please upload .xlsx file", year, 0, List.of("Invalid file format"));
            }
            
            // Parse Excel file
            List<ExcelRowDto> excelData = parseExcelFile(file, errors);
            
            if (excelData.isEmpty()) {
                return new ExcelUploadResponseDto(false, "No valid data found in file", year, 0, errors);
            }
            
            // Check if data already exists for this year
            if (repository.existsByYear(year)) {
                /* log.info("Deleting existing data for year: {}", year); */
                repository.deleteByYear(year);
            }
            
            // Convert and save data
            List<ResearcherRanking> rankings = convertToEntities(excelData, year);
            repository.saveAll(rankings);
            
            /* log.info("Successfully processed {} records for year {}", rankings.size(), year); */
            
            return new ExcelUploadResponseDto(
                true, 
                "File processed successfully", 
                year, 
                rankings.size(), 
                errors
            );
            
        } catch (Exception e) {
            /* log.error("Error processing Excel file: ", e); */
            return new ExcelUploadResponseDto(
                false, 
                "Error processing file: " + e.getMessage(), 
                year, 
                0, 
                List.of(e.getMessage())
            );
        }
    }
    
    private boolean isValidExcelFile(MultipartFile file) {
        return file.getOriginalFilename() != null && 
               file.getOriginalFilename().toLowerCase().endsWith(".xlsx");
    }
    
    private List<ExcelRowDto> parseExcelFile(MultipartFile file, List<String> errors) throws IOException {
        List<ExcelRowDto> excelData = new ArrayList<>();

        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(2);
            Iterator<Row> rowIterator = sheet.iterator();
            
            // Skip header row
            if (rowIterator.hasNext()) {
                rowIterator.next();
            }
            int rowNumber = 2; // Start from 2 (1 is header)
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                
                try {
                    ExcelRowDto excelRow = parseRow(row, rowNumber);
                    if (excelRow != null) {
                        excelData.add(excelRow);
                    }
                } catch (Exception e) {
                    errors.add("Row " + rowNumber + ": " + e.getMessage());
                    /* log.warn("Error parsing row {}: {}", rowNumber, e.getMessage()); */
                }
                
                rowNumber++;
            }
        }
        
        return excelData;
    }
    
    private ExcelRowDto parseRow(Row row, int rowNumber) {
        // Skip empty rows
        if (isRowEmpty(row)) {
            return null;
        }
        
        ExcelRowDto excelRow = new ExcelRowDto();
        excelRow.setRowNumber(rowNumber);
        
        try {
            // Column mapping: position, name, score, position2, category, subcategory, subcategory2, university, codeCountry, codeWorking, profile
            excelRow.setPosition(getIntegerValue(row.getCell(6)));
            excelRow.setName(getStringValue(row.getCell(0)));
            Double scoreDecimal = getDoubleValue(row.getCell(5));
            excelRow.setScore((double)Math.round(scoreDecimal * 100d) / 100d);
            excelRow.setPosition2(getIntegerValue(row.getCell(7)));
            excelRow.setCategory(getStringValue(row.getCell(20)));
            excelRow.setSubcategory(getStringValue(row.getCell(18)));
            excelRow.setSubcategory2(getStringValue(row.getCell(19)));
            excelRow.setUniversity(getStringValue(row.getCell(21)));
            excelRow.setCodeCountry(getStringValue(row.getCell(4)));
            excelRow.setCodeWorking(getStringValue(row.getCell(22)));
            excelRow.setProfile(getStringValue(row.getCell(23)));


            // Validate required fields
            validateRequiredFields(excelRow);
            
        } catch (Exception e) {
            throw new RuntimeException("Error parsing row data: " + e.getMessage());
        }
        
        return excelRow;
    }
    
    private void validateRequiredFields(ExcelRowDto row) {
        if (row.getPosition() == null) {
            throw new RuntimeException("Position is required");
        }
        if (row.getName() == null || row.getName().trim().isEmpty()) {
            throw new RuntimeException("Name is required");
        }
        if (row.getScore() == null) {
            throw new RuntimeException("Score is required");
        }
    }
    
    private boolean isRowEmpty(Row row) {
        if (row == null) return true;
        
        for (int i = 0; i < row.getLastCellNum(); i++) {
            Cell cell = row.getCell(i);
            if (cell != null && cell.getCellType() != CellType.BLANK) {
                return false;
            }
        }
        return true;
    }
    
    private String getStringValue(Cell cell) {
        if (cell == null) return null;
        
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim();
            case NUMERIC:
                return String.valueOf((long) cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            default:
                return null;
        }
    }
    
    private Integer getIntegerValue(Cell cell) {
        if (cell == null) return null;
        
        switch (cell.getCellType()) {
            case NUMERIC -> {
                return (int) cell.getNumericCellValue();
            }
            case STRING -> {
                try {
                    return Integer.parseInt(cell.getStringCellValue().trim());
                } catch (NumberFormatException e) {
                    return null;
                }
            }
            default -> {
                return null;
            }
        }
    }
    
    private Double getDoubleValue(Cell cell) {
        if (cell == null) return null;
        
        switch (cell.getCellType()) {
            case NUMERIC:
                return cell.getNumericCellValue();
            case STRING:
                try {
                    return Double.parseDouble(cell.getStringCellValue().trim());
                } catch (NumberFormatException e) {
                    return null;
                }
            default:
                return null;
        }
    }
    
    private List<ResearcherRanking> convertToEntities(List<ExcelRowDto> excelData, Integer year) {
        return excelData.stream()
            .map(row -> {
                ResearcherRanking ranking = new ResearcherRanking();
                ranking.setYear(year);
                ranking.setPosition(row.getPosition());
                ranking.setName(row.getName());
                ranking.setScore(row.getScore());
                ranking.setPosition2(row.getPosition2());
                ranking.setCategory(row.getCategory());
                ranking.setSubcategory(row.getSubcategory());
                ranking.setSubcategory2(row.getSubcategory2());
                ranking.setUniversity(row.getUniversity());
                ranking.setCodeCountry(row.getCodeCountry());
                ranking.setCodeWorking(row.getCodeWorking());
                ranking.setProfile(row.getProfile());
                return ranking;
            })
            .toList();
    }
}
