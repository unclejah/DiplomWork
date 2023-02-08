package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.User;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);


    /**
     * Установить новый пароль GET <a href="http://localhost:3000/users">...</a>
     **/

    @PostMapping("/set_password")
    public ResponseEntity<NewPassword> setPassword(@RequestBody NewPassword newPassword) {
        return ResponseEntity.ok().build();

    }
    @GetMapping("/me")
    public ResponseEntity<User> getUser() {
        return ResponseEntity.ok().build();
    }

    /** Редактировать пользователя,
     * PUT <a href="http://localhost:3000/ads/">...</a>{id}
     **/

    @PatchMapping("/me")
    public ResponseEntity<User> updateUser(@RequestBody User user){
        return ResponseEntity.ok().build();
    }

    /**
     * Загрузка картинки для пользователя
     */

    @PostMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<User> uploadUserImage(@RequestParam MultipartFile image) {
        return ResponseEntity.ok().build();
    }






















}
