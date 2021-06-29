package ru.akapich.invest_portfolio.service.portfolio.category;

import ru.akapich.invest_portfolio.model.forms.assets.BaseResponseForm;
import ru.akapich.invest_portfolio.model.forms.category.CategoryCreateForm;
import ru.akapich.invest_portfolio.model.portfolio.category.Category;
import java.util.List;
import java.util.Map;

/**
 * Interface for class {@link Category}
 *
 * @author Aleksandr Marakulin
 **/

public interface CategoryService {

	BaseResponseForm getResponseCreateCategory(CategoryCreateForm categoryCreateForm);

	List<String> getListNameCategories();

	void setCategory(String nameCategory);

	Map<String, String> getCurrentCategory();

	void saveNewCategory(CategoryCreateForm categoryCreateForm);
}
