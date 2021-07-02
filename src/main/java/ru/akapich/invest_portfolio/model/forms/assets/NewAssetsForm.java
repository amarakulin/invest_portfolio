package ru.akapich.invest_portfolio.model.forms.assets;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import ru.akapich.invest_portfolio.model.forms.login.RegistrationForm;

import java.math.BigDecimal;

/**
 * Object that get data to save new asset for user.
 *
 * @author Aleksandr Marakulin
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
public class NewAssetsForm {

	private String ticker;
	private String type;
	private BigDecimal amount;

	@Override
	public Object clone() throws CloneNotSupportedException {
		NewAssetsForm newAssetsForm = null;
		try{
			newAssetsForm = (NewAssetsForm) super.clone();
		}
		catch (CloneNotSupportedException e){
			newAssetsForm = NewAssetsForm.builder()
					.amount(amount)
					.type(type)
					.ticker(ticker)
					.build();
		}
		return newAssetsForm;
	}

//	Override
//	public Object clone() throws CloneNotSupportedException {
//		RegistrationForm registrationForm = null;
//		try{
//			registrationForm = (RegistrationForm) super.clone();
//		}
//		catch (CloneNotSupportedException e){
//			registrationForm = RegistrationForm.builder()
//					.name(this.name)
//					.email(this.email)
//					.password(this.password)
//					.rePassword(this.rePassword)
//					.build();
//		}
//		return registrationForm;
//	}
}
