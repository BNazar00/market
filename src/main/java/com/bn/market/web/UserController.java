package com.bn.market.web;

import com.bn.market.entities.Product;
import com.bn.market.entities.User;
import com.bn.market.service.ProductServiceImpl;
import com.bn.market.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserServiceImpl userService;
	@Autowired
	ProductServiceImpl productService;

	@GetMapping("/products")
	public String user(Model model) {
		model.addAttribute("products", productService.findAll());
		return "user/index";
	}

	@PostMapping("/products")
	public String buyProduct(@RequestParam long id, Model model) {
		User user = userService.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		Product product = productService.getProductById(id);

		if (user.getAmountOfMoney() < product.getPrice()) {
			model.addAttribute("product", product);
			return showProductInfo(id, model, "You haven't enough money");
		} else if (userService.getUserProductList(user.getId()).contains(product)) {
			model.addAttribute("product", product);
			return showProductInfo(id, model, "You can only buy one that product");
		}

		userService.buyProduct(user.getId(), product.getId());
		System.out.println(user.getProductList());
		return "redirect:/user/products";
	}

	@GetMapping("product/{id}")
	public String showProductInfo(@PathVariable("id") long id, Model model, String status) {
		Product product = productService.getProductById(id);
		model.addAttribute("status", status);
		model.addAttribute("product", product);
		return "user/product_info";
	}

	@GetMapping("product-list")
	public String showProductList(Model model) {
		Set<Product> products = userService.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getProductList();
		model.addAttribute("products", products);
		return "user/product_list";
	}
}

