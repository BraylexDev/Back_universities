package com.universities.universities.dto;

import java.util.List;

public class ExcelUploadResponseDto {
    private boolean success;
    private String message;
    private Integer year;
    private Integer processedRecords;
    private List<String> errors;

    public ExcelUploadResponseDto() {
    }
    
    public ExcelUploadResponseDto(boolean success, String message, Integer year, Integer processedRecords,
            List<String> errors) {
        this.success = success;
        this.message = message;
        this.year = year;
        this.processedRecords = processedRecords;
        this.errors = errors;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getProcessedRecords() {
        return processedRecords;
    }

    public void setProcessedRecords(Integer processedRecords) {
        this.processedRecords = processedRecords;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
    
    
}
