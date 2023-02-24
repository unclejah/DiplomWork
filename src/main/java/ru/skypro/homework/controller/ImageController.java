package ru.skypro.homework.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.service.impl.ImageServiceImpl;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/image")
public class ImageController {
    private final ImageServiceImpl imageServiceImpl;


    public ImageController(ImageServiceImpl imageServiceImpl) {
        this.imageServiceImpl = imageServiceImpl;
    }

    /**
     * Загрузка картинки для объявления с указанием идентификатора объявления

     */

    @PostMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadAdsImage(@PathVariable  Long id,
                                                       @RequestParam MultipartFile image) {
        return ResponseEntity.ok().build();
    }
    @GetMapping(value = "/user/{id}", produces = {MediaType.IMAGE_PNG_VALUE})
    public ResponseEntity<byte[]> getAdsAvatar(@PathVariable("id") String id) {

        return ResponseEntity.ok().body(imageServiceImpl.downloadImage(id));
    }
    @GetMapping(value = "/{id}", produces = {MediaType.IMAGE_PNG_VALUE})
    public ResponseEntity<byte[]> getAdsAvatar2(@PathVariable("id") String id) {

        return ResponseEntity.ok().body(imageServiceImpl.downloadImage(id));
    }

}
