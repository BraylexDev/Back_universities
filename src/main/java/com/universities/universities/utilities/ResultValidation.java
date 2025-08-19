package com.universities.universities.utilities;

import com.universities.universities.dto.RowFileFilterDTO;

public class ResultValidation {

    private byte[] fileValidated;
    private boolean isCorrect;
    private RowFileFilterDTO universityFilter;
    
    public ResultValidation() {
    }

    public ResultValidation(byte[] fileValidated, boolean isCorrect, RowFileFilterDTO universityFilter) {
        this.fileValidated = fileValidated;
        this.isCorrect = isCorrect;
        this.universityFilter = universityFilter;
    }

    public byte[] getFileValidated() {
        return fileValidated;
    }

    public void setFileValidated(byte[] fileValidated) {
        this.fileValidated = fileValidated;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    public RowFileFilterDTO getUniversityFilter() {
        return universityFilter;
    }

    public void setUniversityFilter(RowFileFilterDTO universityFilter) {
        this.universityFilter = universityFilter;
    }

    
}
