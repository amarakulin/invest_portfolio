package ru.akapich.invest_portfolio.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@PostMapping("/login")
	public String login(){
		return "Login";
	}
}
