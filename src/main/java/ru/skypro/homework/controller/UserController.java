package ru.skypro.homework.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.service.AuthService;
import ru.skypro.homework.service.UserService;
import ru.skypro.homework.service.impl.ImageServiceImpl;

import java.security.Principal;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final AuthService authService;
    private final ImageServiceImpl imageService;

    public UserController(UserService userService, AuthService authService, ImageServiceImpl imageService) {
        this.userService = userService;
        this.authService = authService;
        this.imageService = imageService;
    }

    /**
     * Установить новый пароль GET <a href="http://localhost:3000/users">...</a>
     **/

    @PostMapping("/set_password")
    public ResponseEntity<NewPasswordDto> setPassword(@RequestBody NewPasswordDto newPassword, Principal principal) {
        if (authService.setPassword(newPassword, principal)) {
            return ResponseEntity.ok(newPassword);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

    }
    /**
     * Получение текущего пользователя
     */
    @GetMapping("/me")
    public ResponseEntity<UserDto> getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDto user = userService.getUser(authentication.getName());
        return ResponseEntity.ok(user);
    }

    /** Редактировать пользователя,
     * PUT <a href="http://localhost:3000/ads/">...</a>{id}
     **/

    @PatchMapping("/me")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, Principal principal) {
        UserDto user = userService.updateUser(userDto, principal);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(user);
    }

    /**
     * Загрузка картинки для пользователя
     */

    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UserDto> uploadUserImage(@RequestParam MultipartFile image,Principal principal) {
        UserDto user = userService.getUser(principal.getName());
        user.setImage("/image/user/" + imageService.saveImage(image));
        userService.updateUser(user, principal);
        return ResponseEntity.ok().build();
    }






















}
