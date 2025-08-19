package com.universities.universities.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.universities.universities.model.Ranking;
import com.universities.universities.repository.RankingRepository;

@Service
public class RankingService {

    @Autowired
    private RankingRepository rankingRepository;

    @SuppressWarnings("null")
    public Ranking save(Ranking ranking){
        return rankingRepository.save(ranking);
    }

    public List<Ranking> findByYear(int year){
        return rankingRepository.findByYearOrderByPositionAsc(year);
    }

    public List<Ranking> findTopTen(int year){
        return rankingRepository.findTop10ByYearOrderByPositionAsc(year);
    }

}
