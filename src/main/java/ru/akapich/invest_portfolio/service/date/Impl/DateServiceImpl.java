package ru.akapich.invest_portfolio.service.date.Impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.akapich.invest_portfolio.service.date.DateService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Implementation of {@link DateService} interface
 *
 * @author Aleksandr Marakulin
 **/

@Log4j2
@Service
public class DateServiceImpl implements DateService {

	@Override
	public String getCurrentDateAsString() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH");
		Date date = new Date();
		log.info(String.format("get time: %s", dateFormat.format(date)) );
		return dateFormat.format(date);
	}

	@Override
	public Date getCurrentDateAsObjectByString(String date) throws ParseException {//TODO workout exception
		Date date1 = new SimpleDateFormat("yyyy/MM/dd HH").parse(date);
		return null;
	}
}