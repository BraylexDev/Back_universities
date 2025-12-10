package com.universities.universities.dto;

import java.util.Base64;

import com.universities.universities.model.New;

public class NewDTO {

    private Long id;
    private String title;
    private String description;
    private String image;
    private String url;
    private boolean isActive;
    
    public NewDTO(New noticia) {
        this.id = noticia.getId();
        this.title = noticia.getTitle();
        this.description = noticia.getDescription();
        this.url = noticia.getUrl();
        this.isActive = noticia.isIsActive();
        
        if (noticia.getImage() != null) {
            this.image = "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(noticia.getImage());
        } else {
            this.image = null;
        }
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
}
