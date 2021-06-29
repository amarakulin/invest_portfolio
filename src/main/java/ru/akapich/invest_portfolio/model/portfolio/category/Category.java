package ru.akapich.invest_portfolio.model.portfolio.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * JavaBean domain object that store data of a Category
 *
 * @author Aleksandr Marakulin
 **/

@Entity
@Table(name="t_category")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Category {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String name;
}
