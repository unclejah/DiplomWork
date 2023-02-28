package ru.skypro.homework.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.RegisterReq;
import ru.skypro.homework.dto.RoleDto;
import ru.skypro.homework.entity.Role;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.repository.RoleRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;

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
     * Account login by username and password
     *
     * @param userName - username from client
     * @param password - password from client
     * @return boolean result of login
     */
    @Override
    public boolean login(String userName, String password) {
        UserDetails userDetails = manager.loadUserByUsername(userName);
        String encryptedPassword = userDetails.getPassword();
        return encoder.matches(password, encryptedPassword);
    }

    /**
     * New user registration
     *
     * @param regReq - new user information from client
     * @param role   - users role from client
     * @return boolean result of registration
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




//    private final UserDetailsManager manager;
//
//    private final PasswordEncoder encoder;
//
//    public AuthServiceImpl(UserDetailsManager manager) {
//        this.manager = manager;
//        this.encoder = new BCryptPasswordEncoder();
//    }
//
//    @Override
//    public boolean login(String userName, String password) {
//        if (!manager.userExists(userName)) {
//            return false;
//        }
//        UserDetails userDetails = manager.loadUserByUsername(userName);
//        String encryptedPassword = userDetails.getPassword();
//        String encryptedPasswordWithoutEncryptionType = encryptedPassword.substring(8);
//        return encoder.matches(password, encryptedPasswordWithoutEncryptionType);
//    }
//
//    @Override
//    public boolean register(RegisterReq registerReq, Role role) {
//        if (manager.userExists(registerReq.getUsername())) {
//            return false;
//        }
//        manager.createUser(
//                User.withDefaultPasswordEncoder()
//                        .password(registerReq.getPassword())
//                        .username(registerReq.getUsername())
//                        .roles(role.name())
//                        .build()
//        );
//        return true;
//    }
}
