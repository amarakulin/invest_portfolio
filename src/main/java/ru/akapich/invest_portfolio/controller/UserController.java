package ru.akapich.invest_portfolio.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@GetMapping("/home")
	public String home() {
		return "success";
	}

	@PostMapping("/login")
	public String login(){
		return "Login";
	}
}
