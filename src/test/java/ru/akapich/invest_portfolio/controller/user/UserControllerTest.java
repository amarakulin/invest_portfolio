package ru.akapich.invest_portfolio.controller.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import ru.akapich.invest_portfolio.model.forms.login.RegistrationForm;
import ru.akapich.invest_portfolio.service.user.UserService;
import ru.akapich.invest_portfolio.validator.login.ValidatorController;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 *
 *
 * @author Aleksandr Marakulin
 **/

//@SpringBootTest
//@AutoConfigureMockMvc
//@RunWith(SpringRunner.class)
//@AutoConfigureTestDatabase
//@WebMvcTest(UserController.class)
@SpringBootTest
@AutoConfigureMockMvc
@Import(ValidatorController.class)
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private UserController userController;

	@MockBean
	UserService userService;

	@Test
	public void test() throws Exception{
		assertThat(userController).isNotNull();
	}

	@Test
	public void accessLoginTest() throws Exception{
		this.mockMvc.perform(get("/login"))
				.andDo(print())
				.andExpect(status().is2xxSuccessful());//TODO Ask Front about redirection
	}



	@Test
	public void BadCredentialsTest() throws Exception{
		this.mockMvc.perform(post("/api/auth/login").param("bad", "bad"))
				.andDo(print())
				.andExpect(status().isUnauthorized());
	}

//	@Test
//	public void testSuccessRegistration() throws Exception {
//		RegistrationForm registrationForm = new RegistrationForm("just_test", "just_test@mail.com", "just_test", "just_test");
//
//		this.mockMvc.perform(post("/api/auth/signup")
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(toJson(registrationForm)))
//				.andDo(print())
//				.andExpect(status().isOk())
//				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
//	}

//	@Test
//	public void testGetToken() throws Exception {
//		this.mockMvc.perform(get("/api/auth/token").with(user("lala_test").password("lala_test")))
//				.andDo(print())
//				.andExpect(status().isOk())
//				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
//	}

//	@Test
//	public void testRegistration() throws Exception {
//		RegistrationForm registrationForm = new RegistrationForm("just_test", "just_test@mail.com", "just_test", "just_test");
//
//		this.mockMvc.perform(post("/api/auth/signup")
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(toJson(registrationForm)))
//				.andDo(print())
//				.andExpect(status().isOk())
//				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
//	}

	private String toJson(RegistrationForm registrationForm) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return  objectMapper.writeValueAsString(registrationForm);
	}
}
