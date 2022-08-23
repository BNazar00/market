package com.bn.market.web;

import com.bn.market.entities.User;
import com.bn.market.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MainController {
	@Autowired
	UserServiceImpl userService;

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/")
	public String home(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		String userName = auth.getName();
		if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
			return "redirect:/admin/users";
		}
//		model.addAttribute("name", userName + "!");
		return "redirect:/user/products";
	}

	@GetMapping("/admin/users")
	public String admin(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(userService.getAllUsers());
		model.addAttribute("users", userService.getAllUsers());
		model.addAttribute("name", auth.getName() + "!");

		System.out.println("HI, admin");

		return "admin/index";
	}

	@GetMapping("admin/user/{id}")
	public String showUserInfo(@PathVariable("id") long id, Model model) {
		User user = userService.getUserById(id);
		model.addAttribute("user", user);
		System.out.println(userService.getUserById(id));
		return "admin/user_info";
	}
	@GetMapping("/user/products")
	public String user(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("HI, user");
		return "user/index";
	}
}
