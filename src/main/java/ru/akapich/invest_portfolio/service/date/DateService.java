package ru.akapich.invest_portfolio.service.date;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Interface to work with date format
 *
 * @author Aleksandr Marakulin
 **/

public interface DateService {

	Date getCurrentDateAsObjectByString(String date) throws ParseException;

	LocalDateTime getCurrentTime();
}
