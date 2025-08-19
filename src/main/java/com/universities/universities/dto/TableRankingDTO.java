package com.universities.universities.dto;

public class TableRankingDTO {
    
    private int position;
    private String university;
    private String country;
    private String codeCountry; 
    private int score;

    public TableRankingDTO() {
    }

    public TableRankingDTO(int position, String university, String country, String codeCountry, int score) {
        this.position = position;
        this.university = university;
        this.country = country;
        this.codeCountry = codeCountry;
        this.score = score;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCodeCountry() {
        return codeCountry;
    }

    public void setCodeCountry(String codeCountry) {
        this.codeCountry = codeCountry;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
    
}
