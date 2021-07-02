package ru.akapich.invest_portfolio.controller.asset;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.akapich.invest_portfolio.InvestPortfolioApplication;
import ru.akapich.invest_portfolio.controller.assets.CRUDAssetsController;
import ru.akapich.invest_portfolio.model.forms.assets.NewAssetsForm;
import ru.akapich.invest_portfolio.model.forms.login.RegistrationForm;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.store_assets.AllFinancialAsset;
import ru.akapich.invest_portfolio.repository.portfolio.asset_data.store_assets.AllFinancialAssetRepository;
import ru.akapich.invest_portfolio.service.portfolio.asset_data.store_assets.AllFinancialAssetService;
import ru.akapich.invest_portfolio.validator.login.ValidatorController;

import javax.servlet.Filter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
	private ObjectMapper objectMapper;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private Filter springSecurityFilterChain;

	@Autowired
	private Environment env;

	@Autowired
	private WebApplicationContext context;

	@BeforeEach
	void initDatabase() throws Exception {

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
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));

		this.mockMvc = MockMvcBuilders
				.webAppContextSetup(context)
				.apply(springSecurity())
				.defaultRequest(post("/api/auth/login").with(user(registrationForm.getName()).password(registrationForm.getPassword())))
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
		Map<String, String > map3 = Map.of("symbol", "A3",
				"name", "name3",
				"currency", "USD",
				"exchange", "NYSE",
				"type", "Stock");
		Map<String, String > map4 = Map.of("symbol", "A4",
				"name", "name4",
				"currency", "USD",
				"exchange", "NYSE",
				"type", "Stock");
		list.add(map1);
		list.add(map2);
		list.add(map3);
		list.add(map4);
		allFinancialAssetService.insertAllAssets(list);

	}

	@Test
	public void testSuccessSeveralNewAssets() throws Exception{

		List<NewAssetsForm> newAssetsFormList = new ArrayList<>();
		List<AllFinancialAsset> allFinancialAssetList = allFinancialAssetRepository.findAll();
		for(AllFinancialAsset asset : allFinancialAssetList){
			newAssetsFormList.add(
					NewAssetsForm.builder()
							.ticker(asset.getTicker())
							.type(asset.getIdTypeAsset().getName())
							.amount(BigDecimal.TEN)
							.build());
		}

		this.mockMvc.perform(post("/api/data/newassets")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(newAssetsFormList)))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json(String.format(TEMPLATE_BASE_RESPONSE_FORM, "", SUCCESS_RESULT_CODE)))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	public void testFailedNewAssetsNotExist() throws Exception{

		List<NewAssetsForm> newAssetsFormList = new ArrayList<>();
		AllFinancialAsset allFinancialAsset = allFinancialAssetRepository.findByTicker("A1");
		NewAssetsForm newAssetsForm = NewAssetsForm.builder()
				.ticker(allFinancialAsset.getTicker())
				.type(allFinancialAsset.getIdTypeAsset().getName())
				.amount(BigDecimal.TEN)
				.build();
		newAssetsFormList.add(newAssetsForm);
		NewAssetsForm notExist = (NewAssetsForm) newAssetsForm.clone();
		notExist.setTicker("NOTEXIST");
		newAssetsFormList.add(notExist);

		String errorMessage = String.format("%s: %s", env.getProperty("valid.asset.not_exist"), notExist.getTicker());
		this.mockMvc.perform(post("/api/data/newassets")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(newAssetsFormList)))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json(String.format(TEMPLATE_BASE_RESPONSE_FORM, errorMessage, ERROR_RESULT_CODE)))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	public void testFailedNewAssetsNotInteger() throws Exception{

		List<NewAssetsForm> newAssetsFormList = new ArrayList<>();
		AllFinancialAsset allFinancialAsset1 = allFinancialAssetRepository.findByTicker("A1");
		AllFinancialAsset allFinancialAsset2 = allFinancialAssetRepository.findByTicker("A2");
		NewAssetsForm newAssetsForm1 = NewAssetsForm.builder()
				.ticker(allFinancialAsset1.getTicker())
				.type(allFinancialAsset1.getIdTypeAsset().getName())
				.amount(BigDecimal.TEN)
				.build();
		NewAssetsForm newAssetsForm2 = NewAssetsForm.builder()
				.ticker(allFinancialAsset2.getTicker())
				.type(allFinancialAsset2.getIdTypeAsset().getName())
				.amount(BigDecimal.TEN)
				.build();

		newAssetsForm2.setAmount(BigDecimal.valueOf(12.1));
		newAssetsFormList.add(newAssetsForm1);
		newAssetsFormList.add(newAssetsForm2);


		String errorMessage = String.format("%s: %s", env.getProperty("valid.not_integer"), newAssetsForm2.getTicker());
		this.mockMvc.perform(post("/api/data/newassets")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(newAssetsFormList)))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json(String.format(TEMPLATE_BASE_RESPONSE_FORM, errorMessage, ERROR_RESULT_CODE)))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	public void testFailedNewAssetsRepeat() throws Exception{

		List<NewAssetsForm> newAssetsFormList = new ArrayList<>();
		AllFinancialAsset allFinancialAsset = allFinancialAssetRepository.findByTicker("A1");
		NewAssetsForm newAssetsForm = NewAssetsForm.builder()
				.ticker(allFinancialAsset.getTicker())
				.type(allFinancialAsset.getIdTypeAsset().getName())
				.amount(BigDecimal.TEN)
				.build();
		newAssetsFormList.add(newAssetsForm);
		newAssetsFormList.add((NewAssetsForm) newAssetsForm.clone());

		String errorMessage = env.getProperty("valid.assets.repeat");
		this.mockMvc.perform(post("/api/data/newassets")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(newAssetsFormList)))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json(String.format(TEMPLATE_BASE_RESPONSE_FORM, errorMessage, ERROR_RESULT_CODE)))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	public void testFailedNewAssetsInPortfolio() throws Exception{

		List<NewAssetsForm> newAssetsFormList = new ArrayList<>();
		AllFinancialAsset allFinancialAsset = allFinancialAssetRepository.findByTicker("A1");
		NewAssetsForm newAssetsForm = NewAssetsForm.builder()
				.ticker(allFinancialAsset.getTicker())
				.type(allFinancialAsset.getIdTypeAsset().getName())
				.amount(BigDecimal.TEN)
				.build();
		newAssetsFormList.add(newAssetsForm);

		String errorMessage = String.format("%s: %s", env.getProperty("valid.asset.in_portfolio"), newAssetsForm.getTicker());

		this.mockMvc.perform(post("/api/data/newassets")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(newAssetsFormList)))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json(String.format(TEMPLATE_BASE_RESPONSE_FORM, errorMessage, ERROR_RESULT_CODE)))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}
}
