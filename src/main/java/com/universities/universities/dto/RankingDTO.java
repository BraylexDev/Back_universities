package com.universities.universities.dto;

public class RankingDTO{

    private Long id;
    private String nameUniversity;
    private int position;
    private int score;
    private int year;

    public RankingDTO() {
    }

    public RankingDTO(Long id, String nameUniversity, int position, int score, int year) {
        this.id = id;
        this.nameUniversity = nameUniversity;
        this.position = position;
        this.score = score;
        this.year = year;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameUniversity() {
        return nameUniversity;
    }

    public void setNameUniversity(String nameUniversity) {
        this.nameUniversity = nameUniversity;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    

}
