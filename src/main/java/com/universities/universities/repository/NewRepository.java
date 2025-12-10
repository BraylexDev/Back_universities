package com.universities.universities.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.universities.universities.model.New;

public interface NewRepository extends JpaRepository<New, Long> {

    public List<New> findByIsActive(boolean isActive);
}
