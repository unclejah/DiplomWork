package ru.skypro.homework.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/image")
public class PictureController {


    /**
     * Загрузка картинки для объявления с указанием идентификатора объявления

     */

    @PostMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadAdsPicture(@PathVariable  Long id,
                                                       @RequestParam MultipartFile adsPicture) {
        return ResponseEntity.ok().build();
    }


}
