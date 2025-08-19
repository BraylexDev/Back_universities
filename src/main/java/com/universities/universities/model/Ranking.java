package com.universities.universities.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Ranking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_ranking;

    @ManyToOne
    @JoinColumn(name = "id_university")
    private University university;

    private int position;
    private int score;
    private int year;

    public Ranking() {
    }

    public Ranking(Long idRanking, University university, int position, int score, int year) {
        this.id_ranking = idRanking;
        this.university = university;
        this.position = position;
        this.score = score;
        this.year = year;
    }
    public Long getId_ranking() {
        return id_ranking;
    }
    public void setId_ranking(Long id) {
        this.id_ranking = id;
    }
    public University getUniversity() {
        return university;
    }
    public void setUniversity(University university) {
        this.university = university;
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
