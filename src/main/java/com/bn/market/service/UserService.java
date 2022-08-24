package com.bn.market.service;

import com.bn.market.entities.User;
import com.bn.market.web.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
	User save(UserRegistrationDto registrationDto);

	List<User> getAllAdmins();

	List<User> getAllUsers();

	void updateUser(long id, User user);
}
