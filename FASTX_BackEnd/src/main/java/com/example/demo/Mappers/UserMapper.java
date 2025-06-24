package com.example.demo.Mappers;

import com.example.demo.DTO.UserDTO;
import com.example.demo.Entity.User;

public class UserMapper {
 
	public static UserDTO toDTO(User user) {
        if (user == null) return null;

        return new UserDTO(
                user.getUserId(),
                user.getUserName(),
                user.getEmail(),
                user.getPassword(),
                user.getPhone(),
                user.getAddress(),
                user.getRole()
        );
    }

    public static User toEntity(UserDTO UserDto) {
        if (UserDto == null) return null;

        User user = new User();
        user.setUserId(UserDto.getUserId());
        user.setUserName(UserDto.getUserName());
        user.setEmail(UserDto.getEmail());
        user.setPassword(UserDto.getPassword());
        user.setPhone(UserDto.getPhone());
        user.setAddress(UserDto.getAddress());
        user.setRole(UserDto.getRole());

        return user;
    }
	
	
	
}
