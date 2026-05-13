package com.walletapi.api;

import java.text.ParseException;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.walletapi.dto.UserDTO;
import com.walletapi.service.UserService;

@RestController
@RequestMapping("/users")
public class UserApi {

	private final UserService userService;

	public UserApi(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/listAllUsers")
	public List<UserDTO> listAllUsers() throws ParseException {
		return userService.listAllUsers();

	}

	@PostMapping("/createUser")
	public void createUser(@RequestBody UserDTO userDTO) throws ParseException {
		userService.createUser(userDTO);

	}

	@DeleteMapping("/deleteUser")
	public void deleteUser(@RequestBody Long userCode) throws ParseException {
		userService.deleteUser(userCode);

	}
}
