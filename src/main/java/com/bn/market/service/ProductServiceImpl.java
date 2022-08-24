package com.bn.market.service;

import com.bn.market.entities.Product;
import com.bn.market.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductRepository productRepository;

	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	public Product getProductById(long id) {
		return productRepository.getProductById(id);
	}

	@Override
	public void updateProduct(long id, Product product) {
		Product targetProduct = productRepository.getProductById(id);

		targetProduct.setName(product.getName());
		targetProduct.setPrice(product.getPrice());

		productRepository.save(targetProduct);
	}

	@Override
	public void deleteProduct(long id) {
		Product targetProduct = productRepository.getProductById(id);
		productRepository.delete(targetProduct);
	}

	@Override
	public void saveProduct(Product product) {
		productRepository.save(product);
	}
}
