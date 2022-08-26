package com.bn.market.entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "first_name")
	@Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
	@NotBlank(message = "Name should not be empty")
	private String firstName;

	@Column(name = "last_name")
	@Size(min = 2, max = 30, message = "Surname should be between 2 and 30 characters")
	@NotBlank(message = "Surname should not be empty")
	private String lastName;

	@Email
	private String email;

	private String password;

	@Column(name = "amount_of_money")
	@Min(value = 0, message = "Amount of money should be positive")
//	@NotBlank(message = "Amount of money should not be empty")
	private int amountOfMoney;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "users_roles",
			joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))

	private Set<Role> roles;

	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH},
			fetch = FetchType.EAGER)
	@JoinTable(
			name = "product_list",
			joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id")
	)
	private Set<Product> productList;

	public User() {

	}

	public User(String firstName, String lastName, String email, String password, Set<Role> roles) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public int getAmountOfMoney() {
		return amountOfMoney;
	}

	public void setAmountOfMoney(int amountOfMoney) {
		this.amountOfMoney = amountOfMoney;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Set<Product> getProductList() {
		return productList;
	}

	public void buyProduct(Product product) {
		amountOfMoney -= product.getPrice();

		if (productList == null)
			productList = new HashSet<>();

		productList.add(product);
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", email='" + email + '\'' +
				", roles=" + roles +
				'}';
	}
}
