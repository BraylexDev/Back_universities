package com.universities.universities.controller;

import org.springframework.web.bind.annotation.RestController;

import com.universities.universities.dto.UniversityDTO;
import com.universities.universities.model.University;
import com.universities.universities.service.UniversityService;
import com.universities.universities.utilities.UniversityToDTOConverter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/universities")
public class UniversityController {

    @Autowired
    private UniversityService universityService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/all")
    public List<UniversityDTO> findAll(){
        List<University> universities = universityService.findAll();
        return universities.stream()
            .map((this)::convertToDto)
            .collect(Collectors.toList());
    }
    
    @GetMapping("/name/{name}")
    public List<UniversityDTO> findByName(String name){
        List<University> universities = universityService.findByName(name);
        return universities.stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }
    
    @ResponseStatus(HttpStatus.CREATED) //201
    @PostMapping("/create")
    public UniversityDTO create(@RequestBody UniversityDTO universityDTO) {

        University university = convertToEntity(universityDTO);
        University universityCreated = universityService.save(university);
        return convertToDto(universityCreated);
    }
    
    @ResponseStatus(HttpStatus.NO_CONTENT) //204
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        universityService.deleteById(id);
    }


    //methods for convert entities or dto's
    
    private University convertToEntity(UniversityDTO universityDTO){
        University university = modelMapper.map(universityDTO, University.class);
        return university;
    }

    private UniversityDTO convertToDto(University university){
        UniversityToDTOConverter convDto = new UniversityToDTOConverter();
        modelMapper.addConverter(convDto);
        UniversityDTO universityDTO = modelMapper.map(university, UniversityDTO.class);

        return universityDTO;
    }

    /* private UniversityDTO convertToDto(University university){
        
        Converter<University, UniversityDTO> uniConverter = new Converter<University,UniversityDTO>(){
            public UniversityDTO convert(MappingContext<University, UniversityDTO> context) {
                UniversityDTO uniDTO = new UniversityDTO();
                uniDTO.setId(university.getId_university());
                uniDTO.setRankings(convertToDtoRank(university.getRankings()));
                
                return uniDTO;
            }
        };

        modelMapper.addConverter(uniConverter);

        UniversityDTO universityDTO = modelMapper.map(university, UniversityDTO.class);
        return universityDTO;
    }

    private Set<RankingDTO> convertToDtoRank(Set<Ranking> ranking){
        Set<RankingDTO> rnew = new HashSet<>();
        for (Ranking r : ranking) {
            RankingDTO dto = new RankingDTO();
            dto.setId(r.getId_ranking());
            dto.setPosition(r.getPosition());
            dto.setScore(r.getScore());
            rnew.add(dto);
        }
        return rnew;
    }
 */
}
