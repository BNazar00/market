package com.bn.market.service;

import com.bn.market.entities.Product;

import java.util.List;

public interface ProductService {
	List<Product> findAll();

	Product getProductById(long id);

	void updateProduct(long id, Product product);

	void deleteProduct(long id);

	void saveProduct(Product product);
}
