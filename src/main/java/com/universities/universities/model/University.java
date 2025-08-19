package com.universities.universities.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class University {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_university;

    @OneToMany(mappedBy = "university")
    private Set<Ranking> rankings = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "id_country")
    @JsonIgnore
    private Country country;
    
    private String name;
    private String region;
    private int foundYear;
    private String webSite;
    private String address;
    private String description;
    
    public University() {
    }
    public University(Long id_university, Set<Ranking> rankings, Country country, String name, String region,
            int foundYear, String webSite, String address, String description) {
        this.id_university = id_university;
        this.rankings = rankings;
        this.country = country;
        this.name = name;
        this.region = region;
        this.foundYear = foundYear;
        this.webSite = webSite;
        this.address = address;
        this.description = description;
    }
    public Long getId_university() {
        return id_university;
    }
    public void setId_university(Long id_university) {
        this.id_university = id_university;
    }
    public Set<Ranking> getRankings() {
        return rankings;
    }
    public void setRankings(Set<Ranking> rankings) {
        this.rankings = rankings;
    }
    public Country getCountry() {
        return country;
    }
    public void setCountry(Country country) {
        this.country = country;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getRegion() {
        return region;
    }
    public void setRegion(String region) {
        this.region = region;
    }
    public int getFoundYear() {
        return foundYear;
    }
    public void setFoundYear(int foundYear) {
        this.foundYear = foundYear;
    }
    public String getWebSite() {
        return webSite;
    }
    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
