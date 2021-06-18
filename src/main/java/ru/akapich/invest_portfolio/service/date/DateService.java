package ru.akapich.invest_portfolio.service.date;

import java.text.ParseException;
import java.util.Date;

/**
 * Interface to work with date format
 *
 * @author Aleksandr Marakulin
 **/

public interface DateService {

	String getCurrentDateAsString();

	Date getCurrentDateAsObjectByString(String date) throws ParseException;
}
