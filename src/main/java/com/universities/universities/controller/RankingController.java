package com.universities.universities.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.universities.universities.dto.RankingDTO;
import com.universities.universities.dto.TableRankingDTO;
import com.universities.universities.model.Ranking;
import com.universities.universities.model.University;
import com.universities.universities.service.RankingService;

@RestController
@RequestMapping("/ranking")
public class RankingController {

    @Autowired
    private RankingService rankingService;

    @Autowired
    private ModelMapper modelMapper;

    @ResponseStatus(HttpStatus.CREATED) //201
    @PostMapping("/create")
    public RankingDTO create(@RequestBody RankingDTO rankingDTO){
        Ranking ranking = convertToEntity(rankingDTO);
        Ranking rankingCreated = rankingService.save(ranking);
        return convertToDto(rankingCreated);
    }

    @GetMapping("/rankings")
    public List<RankingDTO> findByYear(int year){
        List<Ranking> rankings = rankingService.findByYear(year);
        return rankings.stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }

    @GetMapping("/table/{year}")
    private List<TableRankingDTO> getTableRankings(@PathVariable("year") int year){
        List<TableRankingDTO> tableRankingDTO =   new ArrayList<>();

        List<Ranking> lRankingDTOs = rankingService.findByYear(year);
        for (Ranking ranking : lRankingDTOs) {
            TableRankingDTO row = new TableRankingDTO();
            row.setPosition(ranking.getPosition());
            row.setScore(ranking.getScore());
            University university = ranking.getUniversity();
            row.setCountry(university.getCountry().getName()); 
            row.setCodeCountry(university.getCountry().getCode());
            row.setUniversity(university.getName());

            tableRankingDTO.add(row);
        }
        return tableRankingDTO;
    }

    @GetMapping("/top/{year}")
    private List<TableRankingDTO> getTopRankings(@PathVariable("year") int year){
        List<TableRankingDTO> tableRankingDTO =   new ArrayList<>();

        List<Ranking> lRankingDTOs = rankingService.findTopTen(year);
        for (Ranking ranking : lRankingDTOs) {
            TableRankingDTO row = new TableRankingDTO();
            row.setPosition(ranking.getPosition());
            row.setScore(ranking.getScore());
            University university = ranking.getUniversity();
            row.setCountry(university.getCountry().getName()); 
            row.setCodeCountry(university.getCountry().getCode());
            row.setUniversity(university.getName());

            tableRankingDTO.add(row);
        }
        return tableRankingDTO;
    }

    //methods for convert entities or dto's
    
    private Ranking convertToEntity(RankingDTO rankingDTO){
        Ranking ranking = modelMapper.map(rankingDTO, Ranking.class);
        return ranking;
    }

    private RankingDTO convertToDto(Ranking ranking){
        RankingDTO rankingDTO = modelMapper.map(ranking, RankingDTO.class);
        return rankingDTO;
    }
}
