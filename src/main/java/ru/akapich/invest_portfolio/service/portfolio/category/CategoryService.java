package ru.akapich.invest_portfolio.service.portfolio.category;

import ru.akapich.invest_portfolio.model.forms.assets.BaseResponseForm;
import ru.akapich.invest_portfolio.model.forms.category.CategoryCreateForm;
import ru.akapich.invest_portfolio.service.portfolio.category.Impl.CategoryServiceImpl;

/**
 * Interface of {@link CategoryServiceImpl}
 *
 * @author Aleksandr Marakulin
 **/

public interface CategoryService {

	BaseResponseForm addNewCategory(CategoryCreateForm categoryCreateForm);

	void setCategory(String nameCategory);

	void deleteCategory(String nameCategory);
}
