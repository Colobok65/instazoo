package ru.shur.instazoo.facade;

import org.springframework.stereotype.Component;
import ru.shur.instazoo.dto.UserDto;
import ru.shur.instazoo.entity.User;

@Component
public class UserFacade {

    public UserDto userToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstname(user.getName());
        userDto.setLastname(user.getLastname());
        userDto.setUsername(user.getUsername());
        userDto.setBio(user.getBio());
        return userDto;
    }
}
