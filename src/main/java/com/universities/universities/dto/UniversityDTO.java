package com.universities.universities.dto;

import java.util.HashMap;

public class UniversityDTO{

    private Long id;
    private String name;
    private String country;
    private String region;
    private int foundYear;
    private String webSite;
    private String address;
    private String description;

    private HashMap<Integer, Integer> rankings = new HashMap<>();

    public UniversityDTO() {
    }

    public UniversityDTO(Long id, String name, String country, String region, int foundYear, String webSite,
            String address, String description, HashMap<Integer, Integer> rankings) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.region = region;
        this.foundYear = foundYear;
        this.webSite = webSite;
        this.address = address;
        this.description = description;
        this.rankings = rankings;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public HashMap<Integer, Integer> getRankings() {
        return rankings;
    }

    public void setRankings(HashMap<Integer, Integer> rankings) {
        this.rankings = rankings;
    }

    
    
}
