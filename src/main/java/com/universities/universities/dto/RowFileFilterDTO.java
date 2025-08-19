package com.universities.universities.dto;

import java.util.List;

public class RowFileFilterDTO {
    
    private List<Long> id_university;
    private List<String> name_university;
    private List<String> country;
    
    public List<Long> getId_university() {
        return id_university;
    }
    public void setId_university(List<Long> id_university) {
        this.id_university = id_university;
    }
    public List<String> getName_university() {
        return name_university;
    }
    public void setName_university(List<String> name_university) {
        this.name_university = name_university;
    }
    public List<String> getCountry() {
        return country;
    }
    public void setCountry(List<String> country) {
        this.country = country;
    }

    
}
