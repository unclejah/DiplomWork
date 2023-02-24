package ru.skypro.homework.service;

import ru.skypro.homework.dto.UserDto;

import java.security.Principal;

public interface UserService {
    UserDto updateUser(UserDto userDto, Principal principal);

    UserDto getUser(String email);
}
