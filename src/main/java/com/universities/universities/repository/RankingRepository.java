package com.universities.universities.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.universities.universities.model.Ranking;
import java.util.List;


public interface RankingRepository extends JpaRepository<Ranking, Long>{
    public List<Ranking> findByYearOrderByPositionAsc(int year);
    public List<Ranking> findTop10ByYearOrderByPositionAsc(int year);
}
