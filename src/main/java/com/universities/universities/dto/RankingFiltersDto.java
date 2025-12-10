package com.universities.universities.dto;

import java.util.List;

public class RankingFiltersDto {
    private List<Integer> years;
    private List<String> categories;
    private List<String> subcategories;
    private List<String> subcategory2List;
    private List<String> countries;
    private List<String> institutions;

    public RankingFiltersDto() {
    }

    public RankingFiltersDto(List<Integer> years, List<String> categories, List<String> subcategories,
            List<String> subcategory2List, List<String> countries, List<String> institutions) {
        this.years = years;
        this.categories = categories;
        this.subcategories = subcategories;
        this.subcategory2List = subcategory2List;
        this.countries = countries;
        this.institutions = institutions;
    }

    public List<Integer> getYears() {
        return years;
    }

    public void setYears(List<Integer> years) {
        this.years = years;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public List<String> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<String> subcategories) {
        this.subcategories = subcategories;
    }

    public List<String> getSubcategory2List() {
        return subcategory2List;
    }

    public void setSubcategory2List(List<String> subcategory2List) {
        this.subcategory2List = subcategory2List;
    }

    public List<String> getCountries() {
        return countries;
    }

    public void setCountries(List<String> countries) {
        this.countries = countries;
    }

    public List<String> getInstitutions() {
        return institutions;
    }

    public void setInstitutions(List<String> institutions) {
        this.institutions = institutions;
    }
    
    
}
