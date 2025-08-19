package com.universities.universities.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_country;
    private String code;
    private String name;
    private String phoneCode;
    private int isActive;

    @OneToMany(mappedBy = "country")
    private Set<University> universities = new HashSet<>();

    public Country() {
    }

    public Country(Long id_country, String code, String name, String phoneCode, int isActive,
            Set<University> universities) {
        this.id_country = id_country;
        this.code = code;
        this.name = name;
        this.phoneCode = phoneCode;
        this.isActive = isActive;
        this.universities = universities;
    }

    public Long getId_country() {
        return id_country;
    }

    public void setId_country(Long id_country) {
        this.id_country = id_country;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public Set<University> getUniversities() {
        return universities;
    }

    public void setUniversities(Set<University> universities) {
        this.universities = universities;
    }
    
    
    
}
