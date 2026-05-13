package com.walletapi.service;

import java.util.List;

import com.walletapi.dto.UserDTO;

public interface UserService {

	void createUser(UserDTO userDTO);

	void deleteUser(Long userCode);

	List<UserDTO> listAllUsers();

}
