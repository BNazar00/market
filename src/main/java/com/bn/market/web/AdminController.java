package com.bn.market.web;

import com.bn.market.entities.Product;
import com.bn.market.entities.User;
import com.bn.market.service.ProductServiceImpl;
import com.bn.market.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	UserServiceImpl userService;
	@Autowired
	ProductServiceImpl productService;

	@GetMapping("users")
	public String admin(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("users", userService.getAllUsers());
		model.addAttribute("name", auth.getName() + "!");
		return "admin/index";
	}

	@GetMapping("user/{id}")
	public String showUserInfo(@PathVariable("id") long id, Model model) {
		User user = userService.getUserById(id);
		model.addAttribute("user", user);
		return "admin/user_info";
	}

	@PatchMapping("user/{id}")
	public String updateUser(@ModelAttribute("user") User user,
							 @PathVariable long id) {
		userService.updateUser(id, user);
		return "redirect:/admin/users";
	}

	@GetMapping("products")
	public String showProducts(Model model) {
		model.addAttribute("products", productService.findAll());
		return "admin/products";
	}

	@PostMapping("products")
	public String addNewProduct(@ModelAttribute Product product) {
		productService.saveProduct(product);
		return "redirect:/admin/products";
	}

	@GetMapping("product/{id}")
	public String showProductInfo(@PathVariable("id") long id, Model model) {
		Product product = productService.getProductById(id);
		model.addAttribute("product", product);
		return "admin/product_info";
	}

	@PatchMapping("product/{id}")
	public String updateProduct(@ModelAttribute("product") Product product,
								@PathVariable long id) {
		productService.updateProduct(id, product);
		return "redirect:/admin/products";
	}

	@DeleteMapping("product/{id}")
	public String deleteProduct(@PathVariable long id) {
		productService.deleteProduct(id);
		return "redirect:/admin/products";
	}

	@GetMapping("add_new_product")
	public String addNewProduct(Model model) {
		model.addAttribute("product", new Product());
		return "admin/add_new_product";
	}
}
