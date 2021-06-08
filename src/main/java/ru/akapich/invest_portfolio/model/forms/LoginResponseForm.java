package ru.akapich.invest_portfolio.model.forms;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponseForm {

	private String error;
	private Integer resultCode;
	private Long userID;
	private String email;
	private String name;
}
