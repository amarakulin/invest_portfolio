package ru.akapich.invest_portfolio.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.akapich.invest_portfolio.model.User;

@RestController
public class UserController {

	@GetMapping("/home")
	public String home() {

		System.out.println("/home! ");
		return "success user IN ";
	}

	@PostMapping("/post")
	public String post(@RequestBody User user) {

		System.out.println("/post! " + user.getLogin());
		return "success user IN " + user.getEmail();
	}
}
