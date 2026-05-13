package com.walletapi.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.walletapi.dto.UserDTO;
import com.walletapi.model.User;
import com.walletapi.repository.UserRepository;
import com.walletapi.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	private final UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public void createUser(UserDTO userDTO) {
		logger.info("==> Executing createUser");

		User user = null;

		if (userDTO.getId() != null) {
			Optional<User> userOptional = userRepository.findById(userDTO.getId());

			if (userOptional.isPresent()) {
				user = userOptional.get();
			}

		} else {
			user = new User();
		}

		user.setEmail(userDTO.getEmail());
		user.setUserName(userDTO.getUserName());
		user.setCreated(new Date());

		userRepository.save(user);

	}

	@Override
	public void deleteUser(Long userCode) {
		logger.info("==> Executing deleteUser");

		userRepository.deleteById(userCode);

	}

	@Override
	public List<UserDTO> listAllUsers() {
		logger.info("==> Executing listAllUsers");

		return userRepository.listAllUsers();
	}

}
