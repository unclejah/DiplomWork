package ru.skypro.homework.service;

import ru.skypro.homework.dto.RegisterReq;
import ru.skypro.homework.dto.RoleDto;

public interface AuthService {
    boolean login(String userName, String password);
    boolean register(RegisterReq registerReq, RoleDto role);
    //boolean setPassword(NewPasswordDto newPassword, Principal principal);
}
