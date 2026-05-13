package com.walletapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.walletapi.dto.UserDTO;
import com.walletapi.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	@Query("""
			    SELECT new com.walletapi.dto.UserDTO(
			        c.id,
			        c.userName,
			        c.email
			    )
			    FROM User c
			""")
	List<UserDTO> listAllUsers();

}
