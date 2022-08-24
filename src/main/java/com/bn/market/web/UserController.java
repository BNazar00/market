package com.bn.market.web;

import com.bn.market.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	ProductServiceImpl productService;

	@GetMapping("/products")
	public String user(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return "user/index";
	}
}
