package ru.akapich.invest_portfolio.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="t_statistic")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Statistic {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String name;

	@OneToOne
	private SetFinancialAssets idSetFinancialAssets;
}
