package ru.skypro.homework.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.RegisterReq;
import ru.skypro.homework.dto.RoleDto;
import ru.skypro.homework.entity.Role;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.repository.RoleRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;

import java.security.Principal;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserDetailsServiceImpl manager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;

    public AuthServiceImpl(UserDetailsServiceImpl manager, UserRepository userRepository, RoleRepository roleRepository) {
        this.manager = manager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = new BCryptPasswordEncoder();
    }

    /**
     * Авторизация по логину и паролю
     */
    @Override
    public boolean login(String userName, String password) {
        UserDetails userDetails = manager.loadUserByUsername(userName);
        String encryptedPassword = userDetails.getPassword();
        return encoder.matches(password, encryptedPassword);
    }

    /**
     * Создание нового пользователя
     */
    @Override
    public boolean register(RegisterReq regReq, RoleDto role) {
        User userFromDB = userRepository.findByUsername(regReq.getUsername());
        if (userFromDB != null) {
            return false;
        }
        User user = new User();
        user.setEmail(regReq.getUsername());
        user.setPassword(encoder.encode(regReq.getPassword()));
        user.setFirstName(regReq.getFirstName());
        user.setLastName(regReq.getLastName());
        user.setPhone(regReq.getPhone());
        Role userRole = new Role();
        userRole.setRoleName("ROLE_" + role.name());
        Set<Role> roles = roleRepository.findAll().stream()
                .filter(r -> r.getRoleName().equals(userRole.getRoleName()))
                .collect(Collectors.toSet());
        if (!roles.isEmpty()) {
            user.setRoles(roles);
        } else {
            user.setRoles(Collections.singleton(userRole));
        }
        userRepository.save(user);
        return true;
    }
    /**
     * Изменение пароля пользователя
     */
    @Override
    public boolean setPassword(NewPasswordDto newPassword, Principal principal) {
        UserDetails userDetails = manager.loadUserByUsername(principal.getName());
        String encryptedPassword = userDetails.getPassword();
        if (encoder.matches(newPassword.getCurrentPassword(), encryptedPassword)) {
            User userFromDB = userRepository.findByUsername(principal.getName());
            userFromDB.setPassword(encoder.encode(newPassword.getNewPassword()));
            userRepository.save(userFromDB);
            return true;
        }
        return false;
    }


}
