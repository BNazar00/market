package com.bn.market.web.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserRegistrationDto {
	@NotBlank(message = "Name should not be empty")
	@Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
	private String firstName;
	@NotBlank(message = "Surname should not be empty")
	@Size(min = 2, max = 30, message = "Surname should be between 2 and 30 characters")
	private String lastName;
	@Email
	private String email;
	@NotBlank
	@Size(min = 4, max = 30, message = "Password should be between 4 and 30 characters")
	private String password;

	public UserRegistrationDto() {

	}

	public UserRegistrationDto(String firstName, String lastName, String email, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
