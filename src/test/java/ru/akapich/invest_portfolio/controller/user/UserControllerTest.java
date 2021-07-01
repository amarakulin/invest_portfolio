package ru.akapich.invest_portfolio.controller.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
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

	private static final int ERROR_RESULT_CODE = 1;
	private static final int SUCCESS_RESULT_CODE = 0;
	private static final String TEMPLATE_BASE_RESPONSE_FORM = "{'error':'%s','resultCode':%d}";
	private static final String TEMPLATE_LOGIN_FORM = "email=%s&password=%s";
	private static final String SUCCESS_RESPONSE_LOGIN = "ok";
	private static final String FAILED_RESPONSE_LOGIN = "Authentication failure";




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

	@Autowired
	private Environment env;

	@MockBean
	private Model model ;

/*
 * General tests UserController
 */

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

/*
* Registration Tests
*/

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
				.andExpect(content().json(String.format(TEMPLATE_BASE_RESPONSE_FORM, "", SUCCESS_RESULT_CODE)))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	public void testFailedRegistrationEmailWithoutAtSign() throws Exception {
		RegistrationForm registrationForm = RegistrationForm.builder()
				.email("registrationmail.com")
				.name("registration_without_at_sign")
				.password("registration_without_at_sign")
				.rePassword("registration_without_at_sign")
				.build();
		String errorMessage = env.getProperty("valid.email");
		this.mockMvc.perform(post("/api/auth/signup")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(registrationForm)))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json(String.format(TEMPLATE_BASE_RESPONSE_FORM, errorMessage, ERROR_RESULT_CODE)))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	public void testFailedRegistrationEmailWithoutMail() throws Exception {
		RegistrationForm registrationForm = RegistrationForm.builder()
				.email("failed_registration@.com")
				.name("failed_registration_with_mail")
				.password("failed_registration_with_mail")
				.rePassword("failed_registration_with_mail")
				.build();
		String errorMessage = env.getProperty("valid.email");
		this.mockMvc.perform(post("/api/auth/signup")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(registrationForm)))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json(String.format(TEMPLATE_BASE_RESPONSE_FORM, errorMessage, ERROR_RESULT_CODE)))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	public void testFailedRegistrationEmailWithSeveralAtSign() throws Exception {
		RegistrationForm registrationForm = RegistrationForm.builder()
				.email("@registration@mail.com")
				.name("registration_several_at_sign")
				.password("registration_several_at_sign")
				.rePassword("registration_several_at_sign")
				.build();
		String errorMessage = env.getProperty("valid.email");
		this.mockMvc.perform(post("/api/auth/signup")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(registrationForm)))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json(String.format(TEMPLATE_BASE_RESPONSE_FORM, errorMessage, ERROR_RESULT_CODE)))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	public void testFailedRegistrationEmptyEmail() throws Exception {
		RegistrationForm registrationForm = RegistrationForm.builder()
				.email("")
				.name("failed_registration_empty_email")
				.password("failed_registration_empty_email")
				.rePassword("failed_registration_empty_email")
				.build();
		String errorMessage = env.getProperty("valid.empty.email");
		this.mockMvc.perform(post("/api/auth/signup")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(registrationForm)))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json(String.format(TEMPLATE_BASE_RESPONSE_FORM, errorMessage, ERROR_RESULT_CODE)))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	public void testFailedRegistrationEmptyName() throws Exception {
		RegistrationForm registrationForm = RegistrationForm.builder()
				.email("failed_registration_empty_name@mail.com")
				.name("")
				.password("failed_registration_empty_name")
				.rePassword("failed_registration_empty_name")
				.build();
		String errorMessage = env.getProperty("valid.size.name");
		this.mockMvc.perform(post("/api/auth/signup")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(registrationForm)))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json(String.format(TEMPLATE_BASE_RESPONSE_FORM, errorMessage, ERROR_RESULT_CODE)))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	public void testFailedRegistrationShortName() throws Exception {
		RegistrationForm registrationForm = RegistrationForm.builder()
				.email("failed_registration_short_name@mail.com")
				.name("a")
				.password("failed_registration_short_name")
				.rePassword("failed_registration_short_name")
				.build();
		String errorMessage = env.getProperty("valid.size.name");
		this.mockMvc.perform(post("/api/auth/signup")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(registrationForm)))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json(String.format(TEMPLATE_BASE_RESPONSE_FORM, errorMessage, ERROR_RESULT_CODE)))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	public void testFailedRegistrationLongName() throws Exception {
		RegistrationForm registrationForm = RegistrationForm.builder()
				.email("failed_registration_long_name@mail.com")
				.name("so_extremely_big_name_of_the_user_more_then_32_symbols")
				.password("failed_registration_long_name")
				.rePassword("failed_registration_long_name")
				.build();
		String errorMessage = env.getProperty("valid.size.name");
		this.mockMvc.perform(post("/api/auth/signup")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(registrationForm)))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json(String.format(TEMPLATE_BASE_RESPONSE_FORM, errorMessage, ERROR_RESULT_CODE)))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	public void testFailedRegistrationExistEmail() throws Exception {
		RegistrationForm registrationFormFirst = RegistrationForm.builder()
				.email("not_unique_user_email@mail.com")
				.name("not_unique_user_email")
				.password("not_unique_user_email")
				.rePassword("not_unique_user_email")
				.build();

		RegistrationForm registrationFormSecond = (RegistrationForm) registrationFormFirst.clone();
		registrationFormSecond.setName("changed_name");
		String errorMessage = env.getProperty("valid.existing.email");

		this.mockMvc.perform(post("/api/auth/signup")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(registrationFormFirst)))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json(String.format(TEMPLATE_BASE_RESPONSE_FORM, "", SUCCESS_RESULT_CODE)))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));

		this.mockMvc.perform(post("/api/auth/signup")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(registrationFormSecond)))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json(String.format(TEMPLATE_BASE_RESPONSE_FORM, errorMessage, ERROR_RESULT_CODE)))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	public void testFailedRegistrationExistName() throws Exception {
		RegistrationForm registrationFormFirst = RegistrationForm.builder()
				.email("not_unique_user_name@mail.com")
				.name("not_unique_user_name")
				.password("not_unique_user_name")
				.rePassword("not_unique_user_name")
				.build();

		RegistrationForm registrationFormSecond = (RegistrationForm) registrationFormFirst.clone();
		registrationFormSecond.setEmail("changed_email@mail.com");
		String errorMessage = env.getProperty("valid.existing.name");

		this.mockMvc.perform(post("/api/auth/signup")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(registrationFormFirst)))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json(String.format(TEMPLATE_BASE_RESPONSE_FORM, "", SUCCESS_RESULT_CODE)))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));

		this.mockMvc.perform(post("/api/auth/signup")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(registrationFormSecond)))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json(String.format(TEMPLATE_BASE_RESPONSE_FORM, errorMessage, ERROR_RESULT_CODE)))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	public void testFailedRegistrationEmptyPassword() throws Exception {
		RegistrationForm registrationForm = RegistrationForm.builder()
				.email("registration_empty_password@mail.com")
				.name("registration_empty_password")
				.password("")
				.rePassword("")
				.build();
		String errorMessage = env.getProperty("valid.size.password");
		this.mockMvc.perform(post("/api/auth/signup")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(registrationForm)))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json(String.format(TEMPLATE_BASE_RESPONSE_FORM, errorMessage, ERROR_RESULT_CODE)))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	public void testFailedRegistrationShortPassword() throws Exception {
		RegistrationForm registrationForm = RegistrationForm.builder()
				.email("registration_big_password@mail.com")
				.name("registration_big_password")
				.password("so_extremely_big_password_of_the_user_more_then_32_symbols")
				.rePassword("so_extremely_big_password_of_the_user_more_then_32_symbols")
				.build();
		String errorMessage = env.getProperty("valid.size.password");
		this.mockMvc.perform(post("/api/auth/signup")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(registrationForm)))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json(String.format(TEMPLATE_BASE_RESPONSE_FORM, errorMessage, ERROR_RESULT_CODE)))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

//	@Test
//	public void testFailedRegistrationPasswordNotMatches() throws Exception {//TODO handle matches passwords
//		RegistrationForm registrationForm = RegistrationForm.builder()
//				.email("not_matches_password@mail.com")
//				.name("not_matches_password")
//				.password("first_password")
//				.rePassword("second_password")
//				.build();
//		String errorMessage = env.getProperty("valid.size.password");
//		this.mockMvc.perform(post("/api/auth/signup")
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(objectMapper.writeValueAsBytes(registrationForm)))
//				.andDo(print())
//				.andExpect(status().isOk())
//				.andExpect(content().json(String.format(TEMPLATE_BASE_RESPONSE_FORM, errorMessage, ERROR_RESULT_CODE)))
//				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
//	}

	@Test
	public void testFailedRegistrationBigPassword() throws Exception {
		RegistrationForm registrationForm = RegistrationForm.builder()
				.email("registration_big_password@mail.com")
				.name("registration_big_password")
				.password("low")
				.rePassword("low")
				.build();
		String errorMessage = env.getProperty("valid.size.password");
		this.mockMvc.perform(post("/api/auth/signup")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(registrationForm)))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json(String.format(TEMPLATE_BASE_RESPONSE_FORM, errorMessage, ERROR_RESULT_CODE)))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

/*
 * Login Tests
 */

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
				.andExpect(content().json(String.format(TEMPLATE_BASE_RESPONSE_FORM, "", SUCCESS_RESULT_CODE)))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));

		this.mockMvc.perform(post("/api/auth/login")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
				.content(String.format(TEMPLATE_LOGIN_FORM, registrationForm.getEmail(), registrationForm.getPassword())))
				.andDo(print())
				.andExpect(content().string(SUCCESS_RESPONSE_LOGIN))
				.andExpect(status().isOk());
	}

	@Test
	public void testFailedLoginWrongEmail() throws Exception{
		RegistrationForm registrationForm = RegistrationForm.builder()
				.email("failed_login_email@mail.com")
				.name("failed_login_email")
				.password("failed_login_email")
				.rePassword("failed_login_email")
				.build();

		String wrongEmail = "different_mail@mail.com";

		this.mockMvc.perform(post("/api/auth/signup")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(registrationForm)))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json(String.format(TEMPLATE_BASE_RESPONSE_FORM, "", SUCCESS_RESULT_CODE)))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));

		this.mockMvc.perform(post("/api/auth/login")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
				.content(String.format(TEMPLATE_LOGIN_FORM, wrongEmail, registrationForm.getPassword())))
				.andDo(print())
				.andExpect(content().string(FAILED_RESPONSE_LOGIN))
				.andExpect(status().is4xxClientError());
	}

	@Test
	public void testFailedLoginWrongPassword() throws Exception{
		RegistrationForm registrationForm = RegistrationForm.builder()
				.email("failed_login_password@mail.com")
				.name("failed_login_password")
				.password("failed_login_password")
				.rePassword("failed_login_password")
				.build();

		String wrongPassword = "different_password";

		this.mockMvc.perform(post("/api/auth/signup")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(registrationForm)))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json(String.format(TEMPLATE_BASE_RESPONSE_FORM, "", SUCCESS_RESULT_CODE)))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));

		this.mockMvc.perform(post("/api/auth/login")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
				.content(String.format(TEMPLATE_LOGIN_FORM, registrationForm.getEmail(), wrongPassword)))
				.andDo(print())
				.andExpect(content().string(FAILED_RESPONSE_LOGIN))
				.andExpect(status().is4xxClientError());
	}



	private String toJson(RegistrationForm registrationForm) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return  objectMapper.writeValueAsString(registrationForm);
	}
}
