package ru.akapich.invest_portfolio.service.portfolio.visualization.Impl.enums;

import ru.akapich.invest_portfolio.model.forms.visualization.FormTable;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * JavaObject to represent headers in the {@link FormTable}
 *
 * @author Aleksandr Marakulin
 **/

public enum HeadersData {
	NAME("name", "Название"),
	TICKER("ticker", "Тикер"),
	TYPE("type", "Тип актива"),
	EXCHANGE("exchange", "Биржа"),
	PRICE("price", "Цена"),
	AMOUNT("amount", "Кол-во"),
	TOTAL("total", "Сумма")
	;

	public final String headerKey;
	public final String headerValue;
	private static final Map<String, String> mapHeader = Collections.unmodifiableMap(initializeHeader());

	HeadersData(String headerKey, String headerValue) {
		this.headerKey = headerKey;
		this.headerValue = headerValue;
	}

	public static Map<String, String> getHeaderData(){
		return mapHeader;
	}

	private static Map<String, String> initializeHeader(){
		Map<String, String> mapHeader = new HashMap<>();
		for(HeadersData item : HeadersData.values()){
			mapHeader.put(item.headerKey, item.headerValue);
		}
		return mapHeader;
	}
}
