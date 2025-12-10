package com.universities.universities.controller;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.universities.universities.dto.NewDTO;
import com.universities.universities.model.New;
import com.universities.universities.service.NewService;


@RestController
@RequestMapping("/api/news")
public class NewController {

    @Autowired
    private NewService newService;

    /* @GetMapping
    public List<New> getAll() {
        return newService.getAll();
    } */

    @GetMapping
    public List<NewDTO> getAll() {
        return newService.getAll().stream()
                .map(NewDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/actives")
    public List<NewDTO> getAllActives() {
        return newService.getAllActives().stream()
                .map(NewDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<New> getNew(@PathVariable Long id) {
        return newService.getForId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<NewDTO> createNew(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("url") String url,
            @RequestParam("isActive") boolean isActive,
            @RequestParam("image") MultipartFile imageFile) {

        New auxNew = new New();
        auxNew.setTitle(title);
        auxNew.setDescription(description);
        auxNew.setUrl(url);
        auxNew.setIsActive(true);
        try {
            auxNew.setImage(imageFile.getBytes());
        } catch (java.io.IOException e) {
            return ResponseEntity.badRequest().build();
        }

        New saved = newService.save(auxNew);
        NewDTO newDTO = new NewDTO(saved);
        return ResponseEntity.ok(newDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<New> updateNew(@PathVariable Long id,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("url") String url,
            @RequestParam("isActive") boolean isActive,
            @RequestParam("image") MultipartFile imageFile) {

        System.out.println("Updating news with ID: " + id);

        New auxNew = new New();
        auxNew.setTitle(title);
        auxNew.setDescription(description);
        auxNew.setUrl(url);
        auxNew.setIsActive(isActive);
        try {
            auxNew.setImage(imageFile.getBytes());
        } catch (java.io.IOException e) {
            return ResponseEntity.badRequest().build();
        }

        New updated = newService.update(id, auxNew);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<NewDTO> actualizarNoticia(
            @PathVariable Long id,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("url") String url,
            @RequestParam("isActive") boolean isActive,
            @RequestParam("image") String imagebase64) {

        Optional<New> noticiaOptional = newService.getForId(id);

        if (!noticiaOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        New auxNew = noticiaOptional.get();
        auxNew.setTitle(title);
        auxNew.setDescription(description);
        auxNew.setUrl(url);
        auxNew.setIsActive(true);

        // Convertir imagen Base64 a byte[]
        if (imagebase64 != null) {
            byte[] imagenBytes = Base64.getDecoder().decode(imagebase64.split(",")[1]);
            auxNew.setImage(imagenBytes);
        }

        // Guardar la noticia actualizada
        New noticiaActualizada = newService.update(id, auxNew);

        // Devolver la noticia actualizada en formato DTO
        NewDTO noticiaDTO = new NewDTO(noticiaActualizada);
        return ResponseEntity.ok(noticiaDTO);
    }

    @PutMapping("/active/{id}")
    public ResponseEntity<NewDTO> updateNewActive(
            @PathVariable Long id,
            @RequestBody Map<String, Boolean> requestBody) {

        Boolean isActive = requestBody.get("isActive");
        Optional<New> noticiaOptional = newService.getForId(id);

        if (!noticiaOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        New auxNew = noticiaOptional.get();

        auxNew.setIsActive(isActive);

        // Guardar la noticia actualizada
        New noticiaActualizada = newService.update(id, auxNew);

        // Devolver la noticia actualizada en formato DTO
        NewDTO noticiaDTO = new NewDTO(noticiaActualizada);
        return ResponseEntity.ok(noticiaDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNew(@PathVariable Long id) {
        newService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
