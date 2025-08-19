package com.universities.universities.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.universities.universities.model.FileData;
import com.universities.universities.repository.FileDataRepository;
@Service
public class FileDataService {

    @Autowired
    private FileDataRepository filerepository;

    public List<FileData> findAll(){
        return filerepository.findAll();
    }
}
