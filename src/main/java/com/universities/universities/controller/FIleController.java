package com.universities.universities.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.universities.universities.dto.FileDataDTO;
import com.universities.universities.model.FileData;
import com.universities.universities.service.ExcelService;
import com.universities.universities.service.FileDataService;



@RestController
@RequestMapping("/api/excel")
public class FIleController {

    private final ExcelService excelService;
    private final FileDataService fileService;

    public FIleController(ExcelService excelService, FileDataService fileService) {
        this.excelService = excelService;
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public ResponseEntity<List<FileDataDTO>> uploadExcel(@RequestParam("file") MultipartFile file) {
        try {
            List<FileDataDTO> data = excelService.parseExcelFile(file);
            return ResponseEntity.ok(data);
        } catch (IOException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Collections.emptyList());
        }
    }
    
    @GetMapping("/datas")
    public ResponseEntity<List<FileData>> getData() {
        List<FileData> data = fileService.findAll();
        return ResponseEntity.ok(data);
    }
    
}
