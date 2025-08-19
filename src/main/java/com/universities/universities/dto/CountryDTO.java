package com.universities.universities.dto;

import java.util.ArrayList;
import java.util.List;

public class CountryDTO {

    private Long id_country;
    private String code;
    private String name;
    private String phoneCode;
    private int isActive;

    private List<String> universities = new ArrayList<>();

    public CountryDTO() {
    }

    public CountryDTO(Long id_country, String code, String name, String phoneCode, int isActive,
            List<String> universities) {
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

    public List<String> getUniversities() {
        return universities;
    }

    public void setUniversities(List<String> universities) {
        this.universities = universities;
    }

    
}
