package com.hexaware.fastx.service;

import java.util.List;

import com.hexaware.fastx.dto.UserDTO;

public interface UserService {

	String registerUser(UserDTO userDTO);

	String loginUser(String email, String password);

	UserDTO getUserById(int id);

	UserDTO updateUser(int id, UserDTO userDTO);

	String deleteUser(int id);

	List<UserDTO> getAllUsers();

	List<UserDTO> getUsersByRole(String role);
}
