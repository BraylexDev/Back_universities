package com.universities.universities.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.universities.universities.model.University;
import com.universities.universities.repository.UniversityRepository;

@Service
public class UniversityService {
    
    @Autowired
    private UniversityRepository universityRepository;

    public List<University> findAll(){
        return universityRepository.findAll();
    }

    public List<University> findByName(String name){
        return universityRepository.findByName(name);
    }

    @SuppressWarnings("null")
    public University save(University university){
        return universityRepository.save(university);    
    }

    @SuppressWarnings("null")
    public void deleteById(Long Id){
        universityRepository.deleteById(Id);
    }

    @SuppressWarnings("null")
    public University getUniversityById(Long id){
        return (University)universityRepository.getReferenceById(id);

    }

    public String getNameUniversity(Long id){
        University university = getUniversityById(id);
        return university.getName();
    }
}
