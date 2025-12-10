package com.universities.universities.model;

import jakarta.persistence.*;

@Entity
public class New {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private boolean isActive;
    
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] image;

    private String url;

    public New() {
    }

    public New(Long id, String title, String description, byte[] image, String url, boolean isActive) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
        this.url = url;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
    
    
}
