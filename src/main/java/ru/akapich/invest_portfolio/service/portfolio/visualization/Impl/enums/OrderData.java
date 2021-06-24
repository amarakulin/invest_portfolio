package ru.akapich.invest_portfolio.service.portfolio.visualization.Impl.enums;

import ru.akapich.invest_portfolio.model.forms.visualization.FormTable;

import java.util.LinkedList;

/**
 * JavaObject to represent order in the {@link FormTable}
 *
 * @author Aleksandr Marakulin
 **/

public enum OrderData {
	NAME("name"),
	TICKER("ticker"),
	TYPE("type"),
	EXCHANGE("exchange"),
	PRICE("price"),
	AMOUNT("amount"),
	TOTAL("total")
	;

	public final String orderItem;
	private static final LinkedList<String> order = initializeOrder();

	public static LinkedList<String> getOrderData(){
		return order;
	}


	private static LinkedList<String> initializeOrder() {
		LinkedList<String> order = new LinkedList<>();
		for(OrderData item : OrderData.values()){
			order.add(item.orderItem);
		}
		return order;
	}

	OrderData(String orderItem) {
		this.orderItem = orderItem;
	}
}
