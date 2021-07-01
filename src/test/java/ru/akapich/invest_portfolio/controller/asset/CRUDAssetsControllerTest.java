package ru.akapich.invest_portfolio.controller.asset;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.session.web.http.SessionRepositoryFilter;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import ru.akapich.invest_portfolio.InvestPortfolioApplication;
import ru.akapich.invest_portfolio.controller.assets.CRUDAssetsController;
import ru.akapich.invest_portfolio.model.forms.assets.NewAssetsForm;
import ru.akapich.invest_portfolio.model.forms.login.RegistrationForm;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.store_assets.AllFinancialAsset;
import ru.akapich.invest_portfolio.parser.info_assets.america.ParseInfoAmericanStock;
import ru.akapich.invest_portfolio.repository.portfolio.asset_data.store_assets.AllFinancialAssetRepository;
import ru.akapich.invest_portfolio.service.portfolio.asset_data.store_assets.AllFinancialAssetService;
import ru.akapich.invest_portfolio.validator.login.ValidatorController;

import javax.servlet.Filter;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.akapich.invest_portfolio.controller.user.UserControllerTest.*;

/**
 * Test class for {@link CRUDAssetsController}
 *
 * @author Aleksandr Marakulin
 **/

@AutoConfigureMockMvc
@SpringBootTest(classes = {
		InvestPortfolioApplication.class,
		CRUDAssetsController.class})
@Import(ValidatorController.class)
@ActiveProfiles("application.properties")
public class CRUDAssetsControllerTest {

	@Autowired
	private AllFinancialAssetService allFinancialAssetService;

	@Autowired
	private AllFinancialAssetRepository allFinancialAssetRepository;

	@Autowired
	private ParseInfoAmericanStock parseInfoAmericanStock;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private Filter springSecurityFilterChain;

	@Autowired
	private WebApplicationContext wac;

	@BeforeEach
	void initDatabase() throws Exception {

		this.mockMvc = MockMvcBuilders
				.webAppContextSetup(this.wac)
				.addFilters(this.springSecurityFilterChain)
				.build();

		List<Map<String, String>> list = new ArrayList<>();
		Map<String, String > map1 = Map.of("symbol", "A1",
				"name", "name1",
				"currency", "USD",
				"exchange", "NYSE",
				"type", "Stock");
		Map<String, String > map2 = Map.of("symbol", "A2",
				"name", "name2",
				"currency", "USD",
				"exchange", "NYSE",
				"type", "Stock");
		list.add(map1);
		list.add(map2);
		allFinancialAssetService.insertAllAssets(list);

	}

	@Test
	public void testSetNewAssets() throws Exception{

		List<NewAssetsForm> newAssetsFormList = new ArrayList<>();
		AllFinancialAsset allFinancialAsset = allFinancialAssetRepository.findByTicker("A1");
		NewAssetsForm newAssetsForm = NewAssetsForm.builder()
				.ticker(allFinancialAsset.getTicker())
				.type(allFinancialAsset.getIdTypeAsset().getName())
				.amount(BigDecimal.TEN)
				.build();
		newAssetsFormList.add(newAssetsForm);



		RegistrationForm registrationForm = RegistrationForm.builder()
				.email("user_1@mail.com")
				.name("user_1")
				.password("user_1")
				.rePassword("user_1")
				.build();

		this.mockMvc.perform(post("/api/auth/signup")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(registrationForm)))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json(String.format(TEMPLATE_BASE_RESPONSE_FORM, "", SUCCESS_RESULT_CODE)))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));

//		this.mockMvc.perform(post("/api/auth/login").with(csrf())
//				.contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
//				.content(String.format(TEMPLATE_LOGIN_FORM, registrationForm.getEmail(), registrationForm.getPassword())))
//				.andDo(print())
//				.andExpect(status().isOk())
//				.andExpect(cookie().exists("JSESSIONID"))
//				.andExpect(content().string(SUCCESS_RESPONSE_LOGIN));


//		RequestBuilder requestBuilder = formLogin()
//				.loginProcessingUrl("/api/auth/login")
//				.userParameter("email")
//				.passwordParam("password")
//				.user("user_1@mail.com")
//				.password("user_1");
//		mockMvc.perform(requestBuilder)
//				.andDo(print())
//				.andExpect(status().isOk())
//				.andExpect(cookie().exists("JSESSIONID"));

//		this.mockMvc.perform(post("/api/data/newassets")
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(objectMapper.writeValueAsBytes(newAssetsFormList)))
//				.andDo(print())
//				.andExpect(status().isOk())
//				.andExpect(content().json(String.format(TEMPLATE_BASE_RESPONSE_FORM, "", SUCCESS_RESULT_CODE)))
//				.andExpect(content().contentType(MediaType.APPLICATION_JSON));

	}
}
