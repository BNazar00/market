package com.bn.market.entities;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product {
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column
	@Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
	@NotBlank(message = "Name should not be empty")
	private String name;
	@Column
//	@NotBlank(message = "Price should not be empty")
	@Min(value = 1, message = "Price should be positive")
	private int price;
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH},
			fetch = FetchType.EAGER)
	@JoinTable(
			name = "product_list",
			joinColumns = @JoinColumn(name = "product_id"),
			inverseJoinColumns = @JoinColumn(name = "user_id")
	)
	private Set<User> userList;

	public Product() {
	}

	public Product(String name, int price) {
		this.name = name;
		this.price = price;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Set<User> getUserList() {
		return userList;
	}

	public void addUserToBuyerList(User user) throws Exception {
		if (user.getAmountOfMoney() < price)
			throw new ArithmeticException("Not enough money!");
		else
			user.setAmountOfMoney(user.getAmountOfMoney() - price);

		if (userList == null)
			userList = new HashSet<>();

		userList.add(user);
	}

	@Override
	public String toString() {
		return "id=" + id +
				", name='" + name + '\'' +
				", price=" + price;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Product product = (Product) o;
		return id == product.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
