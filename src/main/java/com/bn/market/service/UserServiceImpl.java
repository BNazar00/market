package com.bn.market.service;

import com.bn.market.entities.Role;
import com.bn.market.entities.User;
import com.bn.market.repository.UserRepository;
import com.bn.market.web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public User save(UserRegistrationDto registrationDto) {
		User user = new User(registrationDto.getFirstName(),
				registrationDto.getLastName(), registrationDto.getEmail(),
				passwordEncoder.encode(registrationDto.getPassword()), Set.of(new Role("ROLE_USER")));

		return userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).toList();
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public List<User> getAllAdmins() {
		return userRepository.findAll().stream()
				.filter(user -> user.getRoles().contains(new Role("ROLE_ADMIN")))
				.toList();
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll().stream()
				.filter(user -> user.getRoles().contains(new Role("ROLE_USER")))
				.toList();
	}

	public User getUserById(long id) {
		return userRepository.getUserById(id);
	}

	@Override
	public void updateUser(long id, User user) {
		User targetUser = userRepository.getUserById(id);

		targetUser.setFirstName(user.getFirstName());
		targetUser.setLastName(user.getLastName());
		targetUser.setAmountOfMoney(user.getAmountOfMoney());

		userRepository.save(targetUser);
	}
}
