package ru.skypro.homework.service;

import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.RegisterReq;
import ru.skypro.homework.dto.RoleDto;

import java.security.Principal;

public interface AuthService {
    boolean login(String userName, String password);
    boolean register(RegisterReq registerReq, RoleDto role);
    boolean setPassword(NewPasswordDto newPassword, Principal principal);
}
