package ru.akapich.invest_portfolio.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ru.akapich.invest_portfolio.DTO.RequestUserDTO;
import ru.akapich.invest_portfolio.DTO.ResponseUserDTO;

@RestController
@RequiredArgsConstructor
public class ControllerUserDTO {

	private static final Logger logger = LoggerFactory.getLogger(ControllerUserDTO.class);

	@GetMapping("/get")
	public ResponseUserDTO get(@RequestParam(value = "login", required = false) String login,
	                           @RequestParam(value = "email", required = false) String email,
	                           @RequestParam(value = "password", required = false) String password){
		logger.info("RESPONSE!");
		logger.info(String.format("login: %s, email: %s, password: %s", login, email, password));

		return new ResponseUserDTO(login, email, password);
	}

	@PostMapping("/post")
	public String post(@RequestBody RequestUserDTO request){
		logger.info("REQUEST!");
		if (request != null && "alex".equals(request.getLogin())){
			return String.format("Pa - login: %s, email: %s, password: %s, Id: %d", request.getLogin(), request.getEmail(), request.getPassword(), request.getId());
		}
		return "Error";
	}

}
