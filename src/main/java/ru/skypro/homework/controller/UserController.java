package ru.skypro.homework.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.service.UserService;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/users")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Установить новый пароль GET <a href="http://localhost:3000/users">...</a>
     **/

    @PostMapping("/set_password")
    public ResponseEntity<NewPasswordDto> setPassword(@RequestBody NewPasswordDto newPassword) {
            return ResponseEntity.ok(newPassword);

    }
    @GetMapping("/me")
    public ResponseEntity<UserDTO> getUser() {
        UserDTO user = userService.getUser(1);
        return ResponseEntity.ok(user);
    }

    /** Редактировать пользователя,
     * PUT <a href="http://localhost:3000/ads/">...</a>{id}
     **/

    @PatchMapping("/me")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO user){
        UserDTO userDto = userService.updateUser(user);
        if (userDto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(userDto);
    }

    /**
     * Загрузка картинки для пользователя
     */

    @PostMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UserDTO> uploadUserImage(@RequestParam MultipartFile image) {
        return ResponseEntity.ok().build();
    }






















}
