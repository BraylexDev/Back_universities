package com.universities.universities.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.universities.universities.dto.FileDataDTO;
import com.universities.universities.model.FileData;
import com.universities.universities.repository.FileDataRepository;


@Service
public class ExcelService {

    @Autowired
    private FileDataRepository fileRepository;

    public List<FileDataDTO> parseExcelFile(MultipartFile file) throws IOException {
        List<FileDataDTO> result = new ArrayList<>();

        // Usamos XSSFWorkbook para .xlsx
        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(2); // primera hoja

            // Suponiendo que la fila 0 es cabecera
            Iterator<Row> rowIterator = sheet.iterator();
            if (rowIterator.hasNext()) {
                rowIterator.next(); // saltamos cabecera
            }

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                FileDataDTO dto = mapRowToDto(row);
                result.add(dto);
            }
        }

        return result;
    }

    private FileDataDTO mapRowToDto(Row row) {
        FileDataDTO data = new FileDataDTO();
        FileData dataEntity = new FileData();
        // Ejemplo: columna 0 = nombre, columna 1 = email, columna 2 = edad
        Cell c0 = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        Cell c4 = row.getCell(4, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        Cell c5 = row.getCell(5, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        Cell c6 = row.getCell(6, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        Cell c7 = row.getCell(7, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        Cell c18 = row.getCell(18, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        Cell c19 = row.getCell(19, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        Cell c20 = row.getCell(20, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        Cell c21 = row.getCell(21, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        Cell c22 = row.getCell(22, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        Cell c23 = row.getCell(23, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

        data.setName(c0.getStringCellValue());
        data.setCodeCountry(c4.getStringCellValue());
        data.setScore((long) c5.getNumericCellValue());
        data.setPosition((int) c6.getNumericCellValue());
        data.setPosition2((int) c7.getNumericCellValue());
        data.setSubcategory(c18.getStringCellValue());
        data.setSubcategory2(c19.getStringCellValue());
        data.setCategory(c20.getStringCellValue());
        data.setUniversity(c21.getStringCellValue());
        data.setCodeWorking(c22.getStringCellValue());
        data.setProfile(c23.getStringCellValue());

        dataEntity.setName(c0.getStringCellValue());
        dataEntity.setCodeCountry(c4.getStringCellValue());
        dataEntity.setScore((long) c5.getNumericCellValue());
        dataEntity.setPosition((int) c6.getNumericCellValue());
        dataEntity.setPosition2((int) c7.getNumericCellValue());
        dataEntity.setSubcategory(c18.getStringCellValue());
        dataEntity.setSubcategory2(c19.getStringCellValue());
        dataEntity.setCategory(c20.getStringCellValue());
        dataEntity.setUniversity(c21.getStringCellValue());
        dataEntity.setCodeWorking(c22.getStringCellValue());
        dataEntity.setProfile(c23.getStringCellValue());

        fileRepository.save(dataEntity);

        return data;
    }

}
