package ru.akapich.invest_portfolio.service.date.Impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.akapich.invest_portfolio.service.date.DateService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * Implementation of {@link DateService} interface
 *
 * @author Aleksandr Marakulin
 **/

@Log4j2
@Service
public class DateServiceImpl implements DateService {

	private static final String timeZone = "GMT-5";

	@Override
	public LocalDateTime getCurrentTime(){

		Instant nowUtc = Instant.now();
		ZoneId asiaSingapore = ZoneId.of(timeZone);
		ZonedDateTime currentTime = ZonedDateTime.ofInstant(nowUtc, asiaSingapore);
		LocalDateTime localDateTime = currentTime.truncatedTo(ChronoUnit.HOURS).toLocalDateTime();
		log.info(String.format("[+] Get Local[%s]: %s", timeZone, localDateTime));

		return localDateTime;
	}
}