package ru.akapich.invest_portfolio.model.portfolio.history_data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.store_assets.OwnedFinancialAsset;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * JavaBean domain object
 * That represent amount of assert changes on {@link OwnedFinancialAsset}
 *
 * @author Aleksandr Marakulin
 **/

@Entity
@Table(name="t_history_amount")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class HistoryAmount {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String date;

	@Column
	private BigDecimal amount;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_owned_financial_asset", nullable = false)
	private OwnedFinancialAsset ownedFinancialAsset;

	@Override
	public Object clone() throws CloneNotSupportedException {
		HistoryAmount historyAmount = null;
		try {
			historyAmount = (HistoryAmount) super.clone();
		}
		catch (CloneNotSupportedException e){
			historyAmount = HistoryAmount.builder()
					.amount(this.amount)
					.date(this.date)
					.ownedFinancialAsset(this.ownedFinancialAsset)
					.build();
		}
		return historyAmount;
	}
}

// try {
//		 user = (User) super.clone();
//		 } catch (CloneNotSupportedException e) {
//		 user = new User(
//		 this.getFirstName(), this.getLastName(), this.getAddress());
//		 }
//		 user.address = (Address) this.address.clone();
//		 return user;
