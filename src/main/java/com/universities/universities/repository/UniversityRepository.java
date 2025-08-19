package com.universities.universities.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.universities.universities.model.University;

public interface UniversityRepository extends JpaRepository<University, Long>{

    List<University> findByName(String name);
}
