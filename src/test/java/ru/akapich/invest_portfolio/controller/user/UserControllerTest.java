package ru.akapich.invest_portfolio.controller.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import ru.akapich.invest_portfolio.InvestPortfolioApplication;
import ru.akapich.invest_portfolio.model.forms.login.RegistrationForm;
import ru.akapich.invest_portfolio.repository.portfolio.InvestPortfolioRepository;
import ru.akapich.invest_portfolio.repository.user.UserRepository;
import ru.akapich.invest_portfolio.service.user.UserService;
import ru.akapich.invest_portfolio.service.user.impl.UserDetailsServiceImpl;
import ru.akapich.invest_portfolio.validator.login.ValidatorController;


import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for {@link UserController}
 *
 * @author Aleksandr Marakulin
 **/

//@SpringBootTest
//@AutoConfigureMockMvc
//@RunWith(SpringRunner.class)
//@AutoConfigureTestDatabase
//@WebMvcTest(UserController.class)
//@PropertySource("classpath:application-test.properties")
//@ActiveProfiles("test")

//@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = {
		InvestPortfolioApplication.class,
		UserController.class})
@Import(ValidatorController.class)
@ActiveProfiles("application.properties")
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private UserRepository userRepository;


	@Autowired
	private UserController userController;

	@Autowired
	private InvestPortfolioRepository investPortfolioRepository;



	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Autowired
	private UserService userService;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private BindingResult bindingResult;



	@MockBean
	private Model model ;

	@Test
	public void testUserControllerOnNull() throws Exception{
		assertThat(userController).isNotNull();
	}

	@Test
	public void accessLoginTest() throws Exception{
		this.mockMvc.perform(get("/login"))
				.andDo(print())
				.andExpect(status().is2xxSuccessful());//TODO Ask Front about redirection
	}



	@Test
	public void badCredentialsTest() throws Exception{//FIXME Bud test
		this.mockMvc.perform(post("/api/auth/login").param("bad", "bad"))
				.andDo(print())
				.andExpect(status().isUnauthorized());
	}

	@Test
	public void testSuccessRegistration() throws Exception {
		RegistrationForm registrationForm = RegistrationForm.builder()
				.email("success_registration@mail.com")
				.name("success_registration")
				.password("success_registration")
				.rePassword("success_registration")
				.build();

		this.mockMvc.perform(post("/api/auth/signup")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(registrationForm)))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	public void testSuccessLogin() throws Exception{
		RegistrationForm registrationForm = RegistrationForm.builder()
				.email("success_login@mail.com")
				.name("success_login")
				.password("success_login")
				.rePassword("success_login")
				.build();

		this.mockMvc.perform(post("/api/auth/signup")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(registrationForm)))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));

		this.mockMvc.perform(post("/api/auth/login")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
				.content("email=success_login@mail.com&password=success_login"))
				.andDo(print())
				.andExpect(status().isOk());
	}


	private String toJson(RegistrationForm registrationForm) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return  objectMapper.writeValueAsString(registrationForm);
	}
}
