package ru.akapich.invest_portfolio.controller.category;

import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.GeneratorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.akapich.invest_portfolio.model.forms.assets.BaseResponseForm;
import ru.akapich.invest_portfolio.model.forms.category.CategoryCreateForm;
import ru.akapich.invest_portfolio.service.portfolio.category.CategoryService;

import java.util.List;
import java.util.Map;

/**
 * Controller for {@link }
 *
 * @author Aleksandr Marakulin
 **/

@Log4j2
@RestController
@CrossOrigin(origins = "http://localhost:3000/*", allowedHeaders = "*", maxAge = 3600)
@PropertySource("classpath:message.properties")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping("/api/category/create")
	public BaseResponseForm createCategory(@RequestBody CategoryCreateForm categoryCreateForm, Model model){
		return categoryService.addNewCategory(categoryCreateForm);
	}

	@PutMapping("/api/category/set")
	public void setCategory(@RequestBody Map<String, String> name){
		if (name.get("name") != null) {
			categoryService.setCategory(name.get("name"));
		}
	}

	@GetMapping("/api/category/categories")
	public List<String> getAllCategories(){

		return categoryService.getListNameCategories();
	}

	@GetMapping("/api/category/setted")
	public Map<String, String> getCurrentCategory(){

		return categoryService.getCurrentCategory();
	}
}
