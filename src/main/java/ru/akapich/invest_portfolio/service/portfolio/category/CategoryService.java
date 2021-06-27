package ru.akapich.invest_portfolio.service.portfolio.category;

import ru.akapich.invest_portfolio.model.forms.assets.BaseResponseForm;
import ru.akapich.invest_portfolio.model.forms.category.CategoryCreateForm;
import ru.akapich.invest_portfolio.service.portfolio.category.Impl.CategoryServiceImpl;

import java.util.List;
import java.util.Map;

/**
 * Interface of {@link CategoryServiceImpl}
 *
 * @author Aleksandr Marakulin
 **/

public interface CategoryService {

	BaseResponseForm addNewCategory(CategoryCreateForm categoryCreateForm);

	List<String> getListNameCategories();

	void setCategory(String nameCategory);

	void deleteCategory(String nameCategory);

	Map<String, String> getCurrentCategory();
}
