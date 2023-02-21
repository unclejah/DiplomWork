package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.entity.User;

import java.util.List;

@Mapper
public interface UserMapper {
    UserDTO userToUserDto(User user);

    List<UserDTO> userToUserDto(List<User> userList);

    @Mapping(target = "role", ignore = true)
    @Mapping(target = "adsList", ignore = true)
    @Mapping(target = "commentList", ignore = true)
    @Mapping(target = "password", ignore = true)
    User userDtoToUser(UserDTO userDto);

    List<User> userDtoToUser(List<UserDTO> userDto);

}
