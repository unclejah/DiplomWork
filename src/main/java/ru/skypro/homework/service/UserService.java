package ru.skypro.homework.service;

import ru.skypro.homework.dto.UserDTO;

public interface UserService {
    UserDTO updateUser(UserDTO userDto);

    UserDTO getUser(int id);
}
