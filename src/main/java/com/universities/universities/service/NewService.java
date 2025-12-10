package com.universities.universities.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.universities.universities.model.New;
import com.universities.universities.repository.NewRepository;

@Service
public class NewService {

    @Autowired
    private NewRepository newRepository;

    public List<New> getAll() {
        return newRepository.findAll();
    }

    public List<New> getAllActives() {
        return newRepository.findByIsActive(true);
    }

    public Optional<New> getForId(Long id) {
        return newRepository.findById(id);
    }

    public New save(New noticia) {
        return newRepository.save(noticia);
    }

    public New update(Long id, New newUpdated) {
        return newRepository.findById(id).map(auxNew -> {
            auxNew.setTitle(newUpdated.getTitle());
            auxNew.setDescription(newUpdated.getDescription());
            auxNew.setImage(newUpdated.getImage());
            auxNew.setUrl(newUpdated.getUrl());
            auxNew.setIsActive(newUpdated.isIsActive());
            return newRepository.save(auxNew);
        }).orElse(null);
    }

    public void delete(Long id) {
        newRepository.deleteById(id);
    }
}
