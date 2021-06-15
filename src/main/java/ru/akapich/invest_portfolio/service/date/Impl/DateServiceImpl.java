package ru.akapich.invest_portfolio.service.date.Impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.akapich.invest_portfolio.service.date.DateService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Aleksandr Marakulin
 **/

@Log4j2
@Service
public class DateServiceImpl implements DateService {

	@Override
	public String getCurrentDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH");
		Date date = new Date();
		log.info(String.format("get time: %s", dateFormat.format(date)) );
		return dateFormat.format(date);
	}
}

//	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss");
//	LocalDateTime now = LocalDateTime.now();
//  System.out.println(dtf.format(now));

//	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//	Date date = new Date();
//  System.out.println(dateFormat.format(date));
