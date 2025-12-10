package com.universities.universities.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "researcher_rankings", indexes = {
    @Index(name = "idx_year", columnList = "year"),
    @Index(name = "idx_category", columnList = "category"),
    @Index(name = "idx_country", columnList = "codeCountry"),
    @Index(name = "idx_university", columnList = "university")
})
public class ResearcherRanking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private Integer year;
    
    @Column(nullable = false)
    private Integer position;
    
    @Column(nullable = false, length = 255)
    private String name;
    
    @Column(nullable = false)
    private Double score;
    
    private Integer position2;
    
    @Column(length = 100)
    private String category;
    
    @Column(length = 100)
    private String subcategory;
    
    @Column(length = 100)
    private String subcategory2;
    
    @Column(length = 255)
    private String university;
    
    @Column(length = 10)
    private String codeCountry;
    
    @Column(length = 10)
    private String codeWorking;
    
    @Column(columnDefinition = "TEXT")
    private String profile;
    
    @Column(nullable = false)
    private LocalDateTime createdAt;
    
    @Column(nullable = false)
    private LocalDateTime updatedAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public ResearcherRanking() {
    }

    public ResearcherRanking(Long id, Integer year, Integer position, String name, Double score, Integer position2,
            String category, String subcategory, String subcategory2, String university, String codeCountry,
            String codeWorking, String profile, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.year = year;
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
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    
}
