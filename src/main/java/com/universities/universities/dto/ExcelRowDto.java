package com.universities.universities.dto;

public class ExcelRowDto {
    private Integer position;
    private String name;
    private Double score;
    private Integer position2;
    private String category;
    private String subcategory;
    private String subcategory2;
    private String university;
    private String codeCountry;
    private String codeWorking;
    private String profile;
    
    // Row number for error reporting
    private int rowNumber;

    public ExcelRowDto() {
    }
    
    public ExcelRowDto(Integer position, String name, Double score, Integer position2, String category,
            String subcategory, String subcategory2, String university, String codeCountry, String codeWorking,
            String profile, int rowNumber) {
        this.position = position;
        this.name = name;
        this.score = score;
        this.position2 = position2;
        this.category = category;
        this.subcategory = subcategory;
        this.subcategory2 = subcategory2;
        this.university = university;
        this.codeCountry = codeCountry;
        this.codeWorking = codeWorking;
        this.profile = profile;
        this.rowNumber = rowNumber;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Integer getPosition2() {
        return position2;
    }

    public void setPosition2(Integer position2) {
        this.position2 = position2;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public String getSubcategory2() {
        return subcategory2;
    }

    public void setSubcategory2(String subcategory2) {
        this.subcategory2 = subcategory2;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getCodeCountry() {
        return codeCountry;
    }

    public void setCodeCountry(String codeCountry) {
        this.codeCountry = codeCountry;
    }

    public String getCodeWorking() {
        return codeWorking;
    }

    public void setCodeWorking(String codeWorking) {
        this.codeWorking = codeWorking;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }
    
    
}
