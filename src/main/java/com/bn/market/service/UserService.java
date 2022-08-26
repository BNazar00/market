package com.bn.market.service;

import com.bn.market.entities.Product;
import com.bn.market.entities.User;
import com.bn.market.web.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Set;

public interface UserService extends UserDetailsService {
	User save(UserRegistrationDto registrationDto);

	List<User> getAllAdmins();

	List<User> getAllUsers();

	void updateUserById(long id, User user);

	void deleteUserById(long id);

	User getUserByEmail(String email);

	void buyProduct(long userId, long productId);

	Set<Product> getUserProductList(long id);
}
